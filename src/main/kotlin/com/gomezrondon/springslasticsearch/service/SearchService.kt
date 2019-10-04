package com.gomezrondon.springslasticsearch.service


interface SearchService {

    fun searchByFileName(fileName: String): List<String>

    fun searchInFile(searchWord: String): List<String>
}