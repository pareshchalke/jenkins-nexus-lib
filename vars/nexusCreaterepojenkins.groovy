@NonCPS
def call ( String url, String gname, String repolist, String creds ) {
  def nlist = nexusGetallrepo( url )
  def glist = nexusGetgroup( url , gname )
  println "STEP 1 ======================"
  def genlist = nexusGeneratenewlistarray ( nlist as String[], repolist as String[] )

  genlist.each {
    nexusAddrepo(it,url,creds)
    nexusAddrepogroup(it,url,gname,creds)
  }
}
