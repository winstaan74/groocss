package groocss.model

import groovy.transform.CompileStatic
import groovy.transform.ToString

@CompileStatic
@ToString(includeNames=true)
class SectionItem {
    String name
    String targetFilename
    String sourceFilename
    boolean generate = true
}
