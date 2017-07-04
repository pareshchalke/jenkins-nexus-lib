def call ( String[] nexusrepo, String[] repoarray ) {
    def newlist = []
    println repoarray
    repoarray.each { String line ->
        value = nexusrepo.contains(line)
        if (value == false) {
            line && newlist << line
        }
    }
    newlist
}
