@NonCPS
def call ( String param ) {
  param.split("\\r?\\n").each { item ->
    println "Param: ${item}"
  }
}
