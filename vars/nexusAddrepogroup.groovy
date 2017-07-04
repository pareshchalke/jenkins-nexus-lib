@NonCPS
def call ( String list, String url, String gname, String creds ) {
  println "Add repogroup method"
  members = nexusGetgroup(url,gname)
  println "print Addrepogroup members"
  println members
  postdata = nexusGenerategrouppostdata(list,url,members,gname)
  println postdata
  postresponse = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', url: "${url}/service/local/repo_groups/${gname}", authentication: "${creds}", httpMode: 'PUT', requestBody: postdata
}
