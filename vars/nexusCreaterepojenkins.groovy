@NonCPS
def call ( String url, String gname, String repolist, String creds ) {
  println "Createrepojenkins method"
  println repolist
  def nlist = nexusGetallrepo( url )
  def glist = nexusGetgroup( url , gname )

  def genlist = nexusGeneratenewlistarray ( nlist as String[], repolist as String[] )

  genlist.each {
    nexusAddrepo(it,url,creds)
    nexusAddrepogroup(it,url,gname,creds)
  }
}
