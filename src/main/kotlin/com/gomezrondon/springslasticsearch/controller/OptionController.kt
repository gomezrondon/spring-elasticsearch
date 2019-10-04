package com.gomezrondon.springslasticsearch.controller

import com.gomezrondon.springslasticsearch.service.IndexService
import com.gomezrondon.springslasticsearch.service.SearchService
import com.gomezrondon.springslasticsearch.utils.convertToJson
import com.gomezrondon.springslasticsearch.utils.loadFolders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/v1")
class OptionController (private val searchService: SearchService
                        ,private val indexService: IndexService){

    @GetMapping("/test")
    fun getTest():String {
        val response= "test EndPoint ${LocalDateTime.now()}!!"
        println(response)
        return response
    }


    //http://localhost:8080/v1/options/1/javier%20gomez
    @GetMapping("/options/{option}/{word}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOption(@PathVariable option: String, @PathVariable word: String=""): String {
        println("Option Selected: $option")

        when(option) {
            "1" -> {
                val result = searchService.searchByFileName(word)
                return convertToJson(result)
            }
            "2" -> {
                val result = searchService.searchInFile(word)
                return convertToJson(result)
            }
            "3" -> {
                val result = ArrayList<String>(searchService.searchByFileName(word))
                result.add("-------------------------------------------------------")
                val result2 = searchService.searchInFile(word)

                result.addAll(result2)
                result.add("Total: ${result.size}")
                return convertToJson(result)
            }
            else -> {
                println("Error wrong option")
            }
        }

        return ""
    }


    @GetMapping("/options/{option}", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun getResetOption(@PathVariable option: String): String {
        println("Option Selected: $option")
        val folders = loadFolders()

        when(option) {

            "1" -> { // index files in folders
                indexService.indexFilesByName(folders)
                return "Finish indexing by Name File..."
            }
            "2" -> { // index files in folders
                indexService.indexTextFiles(folders)
                return "Finish indexing by text in File..."
            }
            "3" ->{ //index All Now
                // Todo: delete all records
                indexService.indexFilesByName(folders)
                indexService.indexTextFiles(folders)
                return "Finish now-indexing All..."
            }
            else -> {
                println("Error wrong option")
            }
        }

        return "Finish Resetting..."
    }


}