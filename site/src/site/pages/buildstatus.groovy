layout 'layouts/main.groovy', true,
        pageTitle: 'GrooCSS - The Groovy CSS DSL - Continuous integration',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-8 col-lg-pull-0') {
                            include template: 'includes/contribute-button.groovy'
                            h1 {
                                i(class: 'fa fa-circle-o-notch') {}
                                yield ' Continuous integration'
                            }
                            article {
                                p """
                                    Our ${
                                    $a(href: 'http://ci.groovy-lang.org?guest=1', 'continuous integration server')
                                },
                                    sponsored by ${$a(href: 'http://www.jetbrains.com', 'JetBrains')},
                                    builds Groovy against multiple JDK versions, as well as some projects from the community tested
                                    against development versions of Groovy:
                                """
                                hr(class: 'divider')

                                h2 'Groovy builds'

                            }
                        }
                    }
                }
            }
        }
