@NonCPS
def call ( String data ) {
  list = []
  println data
  data.split("\\r?\\n").each { item ->
    println "Param: ${item}"
    item && list << item
  }
  return list
}
