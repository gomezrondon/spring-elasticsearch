package com.gomezrondon.springslasticsearch.service

import com.gomezrondon.springkotlin.entities.DataFile
import com.gomezrondon.springslasticsearch.repository.DataFileRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(private val repository: DataFileRepository): SearchService {

    override fun searchByFileName(fileName: String): List<String> {
        val findByName = repository.findByNameCustomQuery(fileName.toLowerCase(), PageRequest(0,500)).map { it.path }.toList()
        return findByName
    }

    override fun searchInFile(word: String): List<String> {
        val searchWord = """(\w){3,}""".toRegex().findAll(word).map { it.value }.map { it.toLowerCase() }.joinToString(" ")
        val findByName = repository.findByLinesUsingCustomQuery(searchWord, PageRequest(0,500)).map { it.path }.toList()

        return  findByName
    }


}