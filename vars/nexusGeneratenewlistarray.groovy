def call ( String[] nexusrepo, String[] repoarray ) {
    def newlist = []
    println "Generate new list array"
    println repoarray.each {
      println it
    }
    repoarray.each { String line ->
        value = nexusrepo.contains(line)
        if (value == false) {
            line && newlist << line
        }
    }
    newlist
}
