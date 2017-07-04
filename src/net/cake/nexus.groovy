package net.cake;
import groovy.json.*

def getnexusrepo(String url) {
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

def getgroupmembers(String url) {
    def gmemberlist = []
    def response = httpRequest acceptType: 'APPLICATION_JSON', url: "${url}/service/local/repo_groups/cake-sbt"
    def jsonout = response.content
    def json = readJSON text: jsonout
    def out = JsonOutput.toJson(json)
    /*json.data.repositories.each {
        grepo = it.id
        grepo && gmemberlist << it.id
    }*/
    return out
}

def generatelist(String[] nexusrepo, String repofile) {
    def newlist = []
    def workspace = env.WORKSPACE
    def repolist = new File("${workspace}/${repofile}")
    def lines = repolist.readLines()
    lines.each { String line ->
        value = nexusrepo.contains(line)
        if (value == false) {
            line && newlist << line
        }
    }
    newlist
}

@NonCPS
def createrepo(String list, String url) {
  postdata = generatepostdata(list)
  postresponse = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', url: "${url}/service/local/repositories", authentication: 'nexus-admin', httpMode: 'POST', requestBody: postdata
  println('Status: '+postresponse.status)
  println('Response: '+postresponse.content)
}

def addrepo(String list, String url) {
    //println "ADD REPO Method ________________"
    def members = getgroupmembers(url)
    println members
    /*members.each {
        println "All members of the group >>>>>>>>>>>>>>>>>"
        println "$it"
    }*/
    postdata = generategrouppostdata(list,url,members)
    println postdata
    postresponse = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', url: "${url}/service/local/repo_groups/cake-sbt", authentication: 'nexus-admin', httpMode: 'PUT', requestBody: postdata
    //println('Status: '+postresponse.status)
    //println('Response: '+postresponse.content)
}

@NonCPS
def generatepostdata(String str) {
    remoteUri = str
    repodomain = str.split('/')
    reponame = repodomain[2].replace(".","-")
    def builder = new JsonBuilder()
    def root = builder.data {
        repoType("proxy")
        id("$reponame")
        name("$reponame")
        providerRole("org.sonatype.nexus.proxy.repository.Repository")
        exposed(true)
        provider("maven2")
        format("maven2")
        repoPolicy("RELEASE")
        checksumPolicy("WARN")
        remoteStorage(
            remoteStorageUrl: "$remoteUri",
            authentication: null,
            connectionSettings: null
        )
    }
    return builder.toString()
}

@NonCPS
def generategrouppostdata( String str, String url, String list ) {
    println "GENERATE POST DATA FOR GROUP <<<<<<<<<<<<<<<<<<<"
    println list
    remoteUri = str
    repodomain = str.split('/')
    reponame = repodomain[2].replace(".","-")
    def json = new JsonSlurper().parseText(list)
    println json
    json.data.repositories << [id: "$reponame", name: "$reponame", resourceURI: "$url/service/local/repo_groups/cake-sbt/${reponame}"]
    return JsonOutput.toJson(json)
}

return this
