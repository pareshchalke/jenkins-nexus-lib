@NonCPS
def call (String list, String url, String gname, String creds) {
    def members = nexusGetgroup(url)
    postdata = nexusGenerategrouppostdata(list,url,members,gname)
    postresponse = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', url: "${url}/service/local/repo_groups/${gname}", authentication: "${creds}", httpMode: 'PUT', requestBody: postdata
}
