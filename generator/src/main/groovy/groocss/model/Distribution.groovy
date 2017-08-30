package groocss.model

import groovy.transform.CompileStatic

@CompileStatic
class Distribution {
    String name
    String description
    List<Download> packages = []

    void description(String d) { description = d }

    void version(String name, Closure versionSpec) {
        Download pkg = new Download(version:name)
        def clone = versionSpec.rehydrate(pkg,pkg,pkg)
        clone()
        packages.add(pkg)
    }
}
