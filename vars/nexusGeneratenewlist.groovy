def call ( String[] nexusrepo, String repofile ) {
    def newlist = []
    def workspace = env.WORKSPACE
    def repolist = new File("${workspace}/${repofile}")
    def lines = repolist.readLines()
    lines.each { String line ->
        value = nexusrepo.contains(line)
        if (value == false) {
            line && newlist << line
        }
    }
    newlist
}
