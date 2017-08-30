package groocss.model

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class Changelog {
    String version
    List<Issue> issues = []

    Issue issue(String id, String type, String desc) {
        def issue = new Issue(id: id, type: type, description: desc)
        issues.add(issue)
        issue
    }
}
