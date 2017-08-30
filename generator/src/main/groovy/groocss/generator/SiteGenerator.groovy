package groocss.generator

import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration
import groovy.transform.CompileStatic
import groocss.model.*

import java.nio.file.FileSystems
import java.nio.file.WatchEvent

import static java.nio.file.StandardWatchEventKinds.*

@CompileStatic
class SiteGenerator {

    private final static Closure SEMANTIC_SORT = { String v1, String v2 ->
        List<String> items1 = decomposeVersion(v1)
        List<String> items2 = decomposeVersion(v2)
        for (int i=0; i<Math.max(items1.size(),items2.size());i++) {
            if (i>=items2.size()) {
                return 1
            }
            if (i>=items1.size()) {
                return -1
            }
            def p1 = items1[i]
            def p2 = items2[i]
            if (p1.isNumber()) {
                return p2.isNumber() ? p2.toInteger() <=> p1.toInteger() : -1
            } else if (p2.isNumber()) {
                return 1
            } else {
                return p2 <=> p1
            }
        }
        0
    }

    File sourcesDir
    File outputDir

    private MarkupTemplateEngine tplEngine
    private Website website

    void setup() {

        println "Generating website using Groovy ${GroovySystem.version}"

        def tplConf = new TemplateConfiguration()
        tplConf.autoIndent = true
        tplConf.autoNewLine = true
        tplConf.baseTemplateClass = PageTemplate

        def classLoader = new URLClassLoader([sourcesDir.toURI().toURL()] as URL[], this.class.classLoader)
        tplEngine = new MarkupTemplateEngine(classLoader, tplConf, new MarkupTemplateEngine.CachingTemplateResolver())

        website = Website.from(new File(sourcesDir, "website.groovy"))

    }

    void render(String page, String target = null, Map model = [:], String baseDir=null) {
        model.menu = website.menu
        model.currentPage = target
        target = target ?: page
        File root
        if (baseDir) {
            root = new File(outputDir, baseDir)
            model[PageTemplate.BASEDIR] = baseDir
            root.mkdirs()
        } else {
            root = outputDir
        }
        new File(root,"${target}.html").
                write(tplEngine.createTemplateByPath("pages/${page}.groovy").make(model).toString(),
                    'utf-8')
    }

    void generateSite() {
        long sd = System.currentTimeMillis()
        setup()

        def changelogs = ChangelogParser.fetchReleaseNotes()

        renderAdocs()
        renderDocumentation()
        renderPages(changelogs)
        renderChangelogs(changelogs)
        renderReleaseNotes()

        long dur = System.currentTimeMillis() - sd
        println "Generated site into $outputDir in ${dur}ms"
    }

    private List<Section> renderDocumentation() {
        website.sections.each { Section section ->
            section.items.each { SectionItem item ->
                if (item.generate) {
                    println "Generating documentation page [$item.name]"
                    render 'docpage', item.targetFilename, [
                            category: 'Learn',
                            title   : item.name,
                            page    : DocumentationHTMLCleaner.parsePage("${DocUtils.DOCS_BASEURL}/html/documentation/${item.sourceFilename}.html")]
                }
            }
        }
    }

    private List<Page> renderPages(List<Changelog> changelogs) {
        website.pages.each { Page page ->
            println "Rendering individual page [$page.source]"
            if ('changelogs' == page.source) {
                page.model.versions = changelogs.collect{it.version}.sort(SEMANTIC_SORT)
            }
            render page.source, page.target, page.model
        }
    }

    private List<Changelog> renderChangelogs(List<Changelog> changelogs) {
        changelogs.each {
            println "Rendering changelog for Groovy $it.version"
            render 'changelog', "changelog-$it.version", [version: it.version, issues: it.issues], 'changelogs'
        }
    }

    private void renderReleaseNotes() {
        def releaseNotesVersions = new TreeSet<String>(new Comparator<String>() {
            @Override int compare(final String v1, final String v2) { SEMANTIC_SORT.call(v1, v2) as int }
        })
        new File(sourcesDir, 'releasenotes').eachFile { File file ->
            def name = file.name.substring(0, file.name.lastIndexOf('.adoc'))
            def version = name - 'groocss-'
            releaseNotesVersions << version
            println "Rendering release notes for GrooCSS $version"
            render 'release-notes', name, [notes: file.getText('utf-8'), version: version], 'releasenotes'
        }
        render 'releases', 'releases', [versions: releaseNotesVersions]
    }

    private void renderAdocs() {
        def asciidoctor = AsciidoctorFactory.instance
        println "Rendering adoc"

        def adocDir = new File(sourcesDir, "adocs")
        adocDir.eachFileRecurse { File f ->
            if (f.name.endsWith('.adoc')) {
                def header = asciidoctor.readDocumentHeader(f)
                def bn = f.name.substring(0,f.name.lastIndexOf('.adoc'))
                def relativePath = []
                def p = f.parentFile
                while (p!=adocDir) {
                    relativePath << p.name
                    p = p.parentFile
                }
                String baseDir = relativePath ? "adocs${File.separator}${relativePath.join(File.separator)}" : 'adocs'
                render 'docpage', bn, [notes: f.getText('utf-8'), header: header], baseDir
                println baseDir
            }
        }
    }

    static void main(String... args) {
        def sourcesDir = args[0] as File
        def outputDir = args[1] as File
        def generator = new SiteGenerator(sourcesDir: sourcesDir, outputDir: outputDir)
        boolean watchMode = args.length > 2 ? Boolean.valueOf(args[2]) : false

        try { generator.generateSite() }
        catch (e) { e.printStackTrace() }

        if (watchMode) {
            println "Started watch mode"
            def watcher = FileSystems.default.newWatchService()

            sourcesDir.eachDirRecurse { File f ->
                f.toPath().register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY)
            }

            def dirs = ['pages', 'layouts', 'includes', 'html', 'assets', 'css', 'fonts', 'img', 'js', 'vendor']

            while (true) {
                def key = watcher.take()
                def pollEvents = (List<WatchEvent<?>>) key.pollEvents()

                def changed = pollEvents.collect { "${it.context()}".toString() }.join(', ')

                if (!dirs.any(changed.&contains)) {
                    try {
                        println "Regenerating site due to changes in: ${changed}"
                        switch(changed) {
                            case ~/groocss-.*/:
                                generator.renderChangelogs(ChangelogParser.fetchReleaseNotes())
                                break
                            case ~/.*adoc/:
                                generator.renderAdocs()
                                break
                            default:
                                generator.generateSite()
                        }
                    } finally {
                        key.reset()
                    }
                }
            }
        }
    }

    static List<String> decomposeVersion(String version) {
        String qualifier = ''
        if (version.indexOf('-')>0) {
            qualifier = version.substring(version.indexOf('-'))
            version = version - qualifier
        }
        List<String> parts = version.split(/\./).toList()
        if (qualifier) {
            parts << qualifier
        }
        parts
    }
}
