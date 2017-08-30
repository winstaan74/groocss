package groocss.generator

import groocss.model.Changelog

class ChangelogParser {

    //TODO actually get changelogs from github
    static List<Changelog> fetchReleaseNotes() {
        [
            log('0.10') {
                issue '11', 'new', 'Added option (convertUnderline) to convert all underlines in style-classes to dashes.'
                issue '10', 'new', 'Added ability to use three or more element selectors without xor.'
                issue '9', 'new', 'Added ability to configure to use some element names as style-classes ' +
                        'just in case you need "main" for example to be used as a style-class.'
            },
            log('0.9') {
                issue '8', 'new', 'Added mix, tint, shade, and greyscale methods.'
                issue '7', 'new', 'Added saturate, desaturate, fadein, fadeout, fade, and hue, saturation and brightness methods.'
                issue '6', 'new', 'Added many colors methods: rgba, hsl, hsla, lighten, darken, etc.'
            },
            log('0.8') {
                issue '5', 'new', 'Added the ability to create "unattached" styles which can be added and removed from style-groups.'
                issue '4', 'new', 'Also, conversion has been improved and multiple additional methods have been added for handling ' +
                        'plain old Strings, Input/OuputStrems, or Reader/PrinterWriter pairs.'
            },
            log('0.7') {
                issue '3', 'new', 'Pseudo-classes are now refered to using the % sign in place of where : appears in CSS.'
                issue '2', 'new', 'Measurements are now a fully fledged part of the DSL and you can do math with them.\n' +
                        'A Measurement is created by using . for example 22.px'
                issue '1', 'new', 'Math between measurements of different types is converted properly.'
            }
        ]
    }

    static Changelog log(String name, @DelegatesTo(Changelog) Closure c) {
        Changelog log = new Changelog(name)
        c.delegate = log
        c()
        log
    }

}
