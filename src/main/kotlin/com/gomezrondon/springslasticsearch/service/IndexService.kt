package com.gomezrondon.springslasticsearch.service

interface IndexService {

    fun indexTextFiles(folders: List<String>)
    fun indexFilesByName(folders: List<String>)
}