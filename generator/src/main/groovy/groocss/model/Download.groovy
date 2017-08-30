package groocss.model

import groovy.transform.CompileStatic

@CompileStatic
class Download {
    String version
    String releaseNotes
    boolean stable = false

    void releaseNotes(String notes) {
        releaseNotes = notes
    }

    void stable(boolean b) {
        stable = b
    }
}
