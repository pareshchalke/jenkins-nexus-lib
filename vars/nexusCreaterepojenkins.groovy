def call ( String url, String gname, String repolist, String creds ) {
  def nlist = nexusGetallrepo( url )
  println nlist
  def glist = nexusGetgroup( url , gname )
  println glist
  println "STEP 1 ======================"

  def rlist = nexusMultilineinput( repolist )

  def genlist = nexusGeneratenewlistarray ( nlist as String[], repolist as String[] )

  genlist.each {
    nexusAddrepo(it,url,creds)
    nexusAddrepogroup(it,url,gname,creds)
  }
}
