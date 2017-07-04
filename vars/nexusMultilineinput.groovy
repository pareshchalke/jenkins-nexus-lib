@NonCPS
def call ( String data ) {
  println data
  data.split("\\r?\\n").each { item ->
    println "Param: ${item}"
  }
}
