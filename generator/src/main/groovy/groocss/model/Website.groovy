package groocss.model

import groovy.transform.Canonical
import groovy.transform.TypeChecked
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

/**
 * Created by adavis on 8/10/17.
 */
@TypeChecked
class Website {

    class Menu {
        List<MenuGroup> groups = []
        def group(String name, @DelegatesTo(value = MenuGroup, strategy = Closure.DELEGATE_ONLY) Closure c) {
            MenuGroup g = new MenuGroup(name)
            groups << g
            c.delegate = g
            c(this)
        }
        MenuGroup getAt(String name) { groups.find {it.name == name} }
    }

    @Canonical
    static class MenuGroup {
        String name
        List<MenuItem> items = []
        def item(String url, String name, String styleClass = '') {
            items.add new MenuItem(url, name, styleClass)
        }
        def each(Closure c) { items.each(c) }
    }
    @Canonical
    static class MenuItem {
        String link, name, style
    }

    Menu menu
    final List<Page> pages = []
    final List<Distribution> allDownloads = []
    final List<Section> sections = []
    final List<String> allDocVersions = []
    final List<Distribution> distributions = []

    /** Allows referring directly to menu groups by name. */
    def propertyMissing(String name) {
        menu.getAt(name)
    }

    def menu(@DelegatesTo(value = Menu, strategy = Closure.DELEGATE_ONLY) Closure c) {
        menu = new Menu()
        c.delegate = menu
        c(this)
    }

    class PageBuilder {
        def page(String source, Map model) {
            pages << new Page(source: source, target: source, model: model)
        }
    }

    def pages(@DelegatesTo(value = PageBuilder, strategy = Closure.DELEGATE_FIRST) Closure c) {
        def builder = new PageBuilder()
        c.delegate = builder
        c(this)
    }

    class DocBuilder {
        def versions(List<String> versions) { allDocVersions.addAll(versions) }
        def section(String name, String icon,
                    @DelegatesTo(value = Section, strategy = Closure.DELEGATE_FIRST) Closure c) {
            def sec = new Section(name: name, icon: icon)
            sections.add sec
            c.delegate = sec
            c(sec)
        }
    }

    def documentation(@DelegatesTo(value = DocBuilder, strategy = Closure.DELEGATE_FIRST) Closure c) {
        def builder = new DocBuilder()
        c.delegate = builder
        c(this)
    }

    def distribution(String name, @DelegatesTo(value = Distribution, strategy = Closure.DELEGATE_FIRST) Closure c) {
        Distribution dist = new Distribution(name: name)
        c.delegate = dist
        c()
        distributions.add(dist)
    }

    static Website from(File source) {
        CompilerConfiguration config = new CompilerConfiguration()
        def customizer = new ImportCustomizer()
        config.addCompilationCustomizers(customizer)
        customizer.addStaticImport('groocss.generator.DocUtils','DOCS_BASEURL')
        config.scriptBaseClass = 'groovy.util.DelegatingScript'
        GroovyShell shell = new GroovyShell(config)
        def script = shell.parse(source)
        def result = new Website()
        ((DelegatingScript) script).setDelegate(result)
        script.run()

        result
    }

    static def getSocialize() { 'Socialize' }

}
