//import static groocss.model.Website.menu
//import static groocss.model.Website.pages
//import static groocss.model.Website.documentation
//import static groocss.model.Website.distribution

menu {
    group('GrooCSS') {
        item "https://github.com/adamldavis/groocss", 'GitHub'
        item "https://bintray.com/adamldavis/maven/GrooCSS", 'Bintray'
        item "http://blag.groocss.org", 'Blog'
        item "docs/0.10.1/index.html", 'Docs'
        item "https://plugins.gradle.org/plugin/org.groocss.groocss-gradle-plugin", 'Gradle Plugin'
        item 'https://gitlab.com/adamldavis/groocss/pipelines', 'Builds'
    }
    group('About') {
        item 'https://github.com/adamldavis/groocss', 'Source Code'
        item 'about.html', 'What is it?'
        item 'faq.html', 'FAQ'
    }
    group('Socialize') {
        item 'https://twitter.com/groocss',             'Groocss on Twitter',   'fa-twitter'
        item "https://github.com/adamldavis/groocss",   'Source on GitHub',     'fa-github'
        item 'https://github.com/adamldavis/groocss/issues','Report issues',    'fa-bug'
    }
}

pages {
    page 'index', [distributions: distributions]
    page 'download', [distributions: distributions]
    page 'documentation', [docSections: sections]
}

documentation {
    versions( ['0.7.2', '0.8', '0.9', '0.10.1'] )

    section('Getting started','fa-graduation-cap') {
        //          NAME                                     TARGET HTML         DOCPAGE HTML                  GENERATE
        item 'Download GrooCSS',                             'download',         'download',                    false
        item 'Install GrooCSS',                              'install',          'core-getting-started'
        item 'Differences with CSS',                         'differences',      'core-differences-css'
        item 'Integrating GrooCSS into applications',        'integrating',      'guide-integrating'
        item 'Style guide',                                  'style-guide',      'style-guide'
        item 'FAQ',                                          'faq',              'faq'
    }
    section('Next Steps', 'fa-gears') {
        item 'Reporting issues',                             'reporting-issues', 'reporting-issues'
        item 'Contributing',                                 'contribute', 'contribute'
    }
}
distribution('GrooCSS 0.10') {
    description 'Support for long CSS-like selectors, and convertUnderline'
    version('0.10') {
        stable true
        releaseNotes '''Added option (convertUnderline) to convert all underlines in style-classes to dashes.
Added ability to use three or more element selectors without xor.'''
    }
    version('0.10.1') {
        stable true
        releaseNotes 'Added getMain() and added convertUnderline to Gradle-plugin'
    }
}
distribution('GrooCSS 0.9') {
    description 'Added tons of color related methods'
    version('0.9') {
        stable true
    }
}
distribution('GrooCSS 0.8') {
    description 'Added support for detached styles and different input types'
    version('0.8') {
        stable true
    }
}
distribution('GrooCSS 0.7') {
    description 'Full support of psuedo-classes and Measurements'
    version('0.7') {
        stable true
    }
}


