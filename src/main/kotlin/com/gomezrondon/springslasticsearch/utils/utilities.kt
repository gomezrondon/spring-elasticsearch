package com.gomezrondon.springslasticsearch.utils

import com.google.gson.GsonBuilder
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}


fun loadFolders() = File("repository${File.separator}white-list.txt").readLines().filter { !it.startsWith("--") }
fun dontSearchList() = File("repository${File.separator}black-list.txt").readLines()
fun lastFileRead() = File("repository${File.separator}lastFile.txt").readLines()


fun filterBlackListPath(noSearchList: List<String>, it: File): Boolean {
    var exist = true
    for (dir: String in noSearchList) {
        if (it.absolutePath.startsWith(dir)) {
            exist = false
        }
    }
    return exist
}


fun convertToJson(objet:Any):String{
    val gson = GsonBuilder().setPrettyPrinting().create() // for pretty print feature
    val jsonStr : String = gson.toJson(objet)
    return jsonStr
}