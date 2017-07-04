@NonCPS
import groovy.json.*

def call ( String str, String url, String list , String gname) {
    remoteUri = str
    repodomain = str.split('/')
    reponame = repodomain[2].replace(".","-")
    def json = new JsonSlurper().parseText(list)
    json.data.repositories << [id: "$reponame", name: "$reponame", resourceURI: "$url/service/local/repo_groups/${gname}/${reponame}"]
    return JsonOutput.toJson(json)
}
