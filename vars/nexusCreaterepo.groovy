def call ( String url, String gname, String repolist, String creds ) {
  def nlist = nexusGetallrepo( url )
  def glist = nexusGetgroup( url , gname )

  def genlist = nexusGeneratenewlist ( nlist as String[], repofile )

  genlist.each {
    nexusaddrepo(it,url,creds)
    nexusAddrepogroup(it,url,gname,creds)
  }
}
