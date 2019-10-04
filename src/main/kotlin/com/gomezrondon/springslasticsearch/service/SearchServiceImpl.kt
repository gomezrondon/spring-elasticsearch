package com.gomezrondon.springslasticsearch.service

import com.gomezrondon.springkotlin.entities.DataFile
import com.gomezrondon.springslasticsearch.repository.DataFileRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(private val repository: DataFileRepository): SearchService {

    override fun searchByFileName(fileName: String): List<String> {
        val word = fileName.toLowerCase()
        var findByName = repository.findByNameCustomQuery(word, PageRequest(0,500)).map { it.path }.toMutableList()
        val toList = repository.findByNameUsingRegex(".*$word.*", PageRequest(0, 500)).map { it.path }.toList()
        findByName.addAll(toList)
        return findByName
    }

    override fun searchInFile(word: String): List<String> {
        val searchWord = """(\w){3,}""".toRegex().findAll(word).map { it.value }.map { it.toLowerCase() }.joinToString(" ")
        val findByName = repository.findByLinesUsingCustomQuery(searchWord, PageRequest(0,500)).map { it.path }.toList()

        return  findByName
    }


}