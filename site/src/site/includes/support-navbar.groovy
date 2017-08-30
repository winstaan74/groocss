
ul(class: 'nav-sidebar') {
    [
            'support': 'Support',
            'index': 'Contribute',
            'reporting-issues': 'Reporting issues',
            'mailing-lists': 'Mailing-lists',
            'thanks': 'Thanks',
    ].each { page, label ->
        if (currentPage == page) {
            li(class: 'active') { a(href: relative("${page}.html")) { strong(label) } }
        } else {
            li { a(href: "${page}.html", label) }
        }
    }
}
br()
include unescaped: 'html/twitter-timeline.html'
