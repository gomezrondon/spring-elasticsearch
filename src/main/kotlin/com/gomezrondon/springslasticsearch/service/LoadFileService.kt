package com.gomezrondon.springslasticsearch.service

interface LoadFileService {
    fun readFile(file: String): MutableList<String>
}