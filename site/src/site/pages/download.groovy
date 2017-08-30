layout 'layouts/main.groocss', true,
        pageTitle: 'GrooCSS - GrooCSS CSS DSL - Download',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row-fluid') {
                    div(class: 'col-lg-3') {
                        ul(class: 'nav-sidebar') {
                            li(class: 'active') {
                                a(href: 'download.html') { strong('Download GrooCSS') }
                            }
                            li {
                                a(href: '#distro', class: 'anchor-link', 'Distributions')
                            }
                            li {
                                a(href: '#buildtools', class: 'anchor-link', 'From your build tools')
                            }
                            li {
                                a(href: '#requirements', class: 'anchor-link', 'System requirements')
                            }
                            li {
                                a(href: 'versioning.html', 'GrooCSS version scheme')
                            }
                            li {
                                a(href: 'releases.html', 'Release notes')
                            }
                        }
                    }

                    div(class: 'col-lg-8 col-lg-pull-0') {
                        include template: 'includes/contribute-button.groocss'
                        h1 {
                            i(class: 'fa fa-cloud-download') {}
                            yield ' Download'
                        }
                        def linkVersionToDownload = distributions.collect { it.packages }.flatten().find { it.stable }.version
                        button(id: 'big-download-button', type: 'button', class: 'btn btn-default',
                                title: "Download GrooCSS ${linkVersionToDownload}",
                                onclick: "window.location.href=\"https://dl.bintray.com/groocss/maven/apache-groocss-sdk-${linkVersionToDownload}.zip\"") {
                            i(class: 'fa fa-download') {}
                            yield ' Download'
                        }
                        article {
                            p {
                                yield 'In this download area, you will be able to download the '
                                a(href: '#distro', 'distribution')
                                yield ' (binary and source), the Windows installer (for some of the versions) and the documentation for GrooCSS.'
                            }
                            p {
                                yield 'All downloads (except the source download) are hosted in '
                                a(href: 'http://bintray.com/groocss/', 'Bintray\'s GrooCSS repository')
                                yield '. Registering on Bintray allows you to rate, review, and register for new version notifications.'
                                a(href: 'https://dl.bintray.com/groocss/maven/', '[direct link to the downloads]')
                            }
                        }
                        hr(class: 'divider')

                        a(name: 'distro') {}
                        article {
                            h1 'Distributions'
                            p 'You can download a binary, a source or a documentation bundle, as well as a bundle of all three.'

                            distributions.each { dist ->
                                h2 {
                                    i(class: 'fa fa-star') {}
                                    yield " ${dist.name}"
                                }
                                if (dist.description) {
                                    p {
                                        yield dist.description
                                    }
                                }
                            }
                            article {
                                h3 'Changelog'

                                p {
                                    yield 'You can also read the changelogs for '
                                    a(href: "changelogs.html", 'other versions')
                                    yield '.'
                                }
                            }
                        }

                        hr(class: 'divider')

                        a(name: 'buildtools') {}
                        article {
                            h1 'From your build tools'
                            p 'If you wish to add GrooCSS as a dependency in your projects, you can refer to the GrooCSS JARs in the dependency section of your project build file descriptor:'
                            table(class: 'table') {
                                thead {
                                    tr {
                                        th 'Gradle'
                                        th 'Maven'
                                        th 'Explanation'
                                    }
                                }
                                tbody {
                                    tr {
                                        td {
                                            code 'org.groocss:groocss:x.y.z'
                                        }
                                        td {
                                            code '&lt;groupId&gt;org.groocss&lt;/groupId&gt;'
                                            br()
                                            code '&lt;artifactId&gt;groocss&lt;/artifactId&gt;'
                                            br()
                                            code '&lt;version&gt;x.y.z&lt;/version&gt;'
                                        }
                                        td 'Just the core of GrooCSS without the modules (see below). Also includes jarjar\'ed versions of Antlr, ASM, and Commons-CLI.'
                                    }
                                }
                            }
                            h3 'Maven repositories'
                            p "GrooCSS releases are downloadable from ${$a(href:'http://repo1.maven.org/maven2/org/codehaus/groocss/','Maven Central')} or ${$a(href:'http://jcenter.bintray.com/org/codehaus/groocss/','JCenter')}."
                            p "GrooCSS snapshots are downloadable from ${$a(href:'https://oss.jfrog.org/oss-snapshot-local/org/codehaus/groocss','JFrog OpenSource Snapshots repository')}."
                        }
                        hr(class: 'divider')
                    }
                }
            }
        }
