def call ( String url, String gname, String repolist, String creds ) {
  def nlist = nexusGetallrepo( url )
  def glist = nexusGetgroup( url , gname )

  def genlist = nexusGeneratenewlist ( nlist as String[], repolist )

  genlist.each {
    nexusAddrepo(it,url,creds)
    nexusAddrepogroup(it,url,gname,creds)
  }
}
