def call ( String url ) {
  def nexuslist = []
  def response = httpRequest acceptType: 'APPLICATION_JSON', url: "${url}/service/local/repositories"
  def jsonout = response.content
  def json = readJSON text: jsonout
  json.data.each {
      repo = it.remoteUri
      repo && nexuslist << it.remoteUri
  }
  nexuslist
}
