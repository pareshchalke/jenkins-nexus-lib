def call ( String list, String url, String creds ) {
  postdata = nexusGeneratepostdata(list)
  postresponse = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', url: "${url}/service/local/repositories", authentication: "${creds}", httpMode: 'POST', requestBody: postdata
}
