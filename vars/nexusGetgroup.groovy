import groovy.json.*

def call ( String url, String gname ) {
    def gmemberlist = []
    def response = httpRequest acceptType: 'APPLICATION_JSON', url: "${url}/service/local/repo_groups/${gname}"
    def jsonout = response.content
    def json = readJSON text: jsonout
    def out = JsonOutput.toJson(json)
    println "reading group info"
    println out
    return out
}
