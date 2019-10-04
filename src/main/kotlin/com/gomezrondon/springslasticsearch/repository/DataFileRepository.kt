package com.gomezrondon.springslasticsearch.repository

import com.gomezrondon.springkotlin.entities.DataFile
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

//https://www.baeldung.com/spring-data-elasticsearch-tutorial

interface DataFileRepository: ElasticsearchRepository<DataFile, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    fun findByNameCustomQuery(name: String, pageable: Pageable): Page<DataFile>

    @Query("{\"bool\": {\"must\": [{\"match\": {\"lines\": \"?0\"}}]}}")
    fun findByLinesUsingCustomQuery(name: String, pageable: Pageable): Page<DataFile>



/*  NO Funcionan como se espera, hay que investigar mas

    fun findByName(name: String): List<DataFile>

    fun findByLines(value: String): List<DataFile>

    @Query("{\"bool\": {\"should\": [{\"wildcard\": {\"name\": \"?0\"}}]}}")
    fun findByNameUsingCustomQuery(name: String): List<DataFile>

    @Query("{\"regexp\":{\"name\":{\"value\": \"?0\"}}}")
    fun findByNameUsingRegex(value: String): List<DataFile>

    @Query("{\"regexp\":{\"lines\":{\"value\": \"?0\"}}}")
    fun findByLinesUsingRegex(value: String): List<DataFile>*/

}