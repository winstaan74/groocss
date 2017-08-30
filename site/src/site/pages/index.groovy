import groocss.model.Distribution

layout 'layouts/main.groovy', true,
        pageTitle: 'GrooCSS: code CSS in Groovy',
        mainContent: contents {
            div(id: 'band', class: 'band') {

            }
            div(id: 'content') {
                include unescaped: 'html/index.html'

                def firstDists = distributions.keySet().take(3)

                section(class: 'row colset-3-article first-event-row') {
                    h1 { strong "Releases" }
                    firstDists.each { Distribution distribution ->
                        String name = distribution.name
                        article {
                            div(class: 'content') {
                                h1 {name}
                                yieldUnescaped distribution.description
                            }
                        }
                    }
                }
                section(class: 'row  last-event-row') {
                    p(class: 'text-center') {
                        yield "For more info see the "
                        a(href: relative("changelogs.html")) { strong('Changelogs') }
                        yield " page"
                    }
                }

            }
            include unescaped: 'html/twitter-timeline.html'
        }
