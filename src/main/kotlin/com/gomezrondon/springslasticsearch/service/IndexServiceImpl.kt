package com.gomezrondon.springslasticsearch.service

import com.gomezrondon.springkotlin.entities.DataFile
import com.gomezrondon.springslasticsearch.repository.DataFileRepository
import com.gomezrondon.springslasticsearch.utils.*
import org.springframework.stereotype.Service
import java.io.File

@Service
class IndexServiceImpl(private val repository: DataFileRepository): IndexService {



    override fun indexFilesByName(folders: List<String>) {

        folders.parallelStream().forEach { folder ->
             indexOnlyByfileName(listOf(folder)) // write to mongo

        }

    }

    private fun indexOnlyByfileName(folders: List<String>) {
        val noSearchList = dontSearchList()
        val textFileList = listOf("txt","sql","java","py","bat","csv","kt","kts")


        folders.parallelStream().forEach { folder ->
            //folders.forEach { folder ->
            var DFList = mutableListOf<DataFile>()
//            val existList: List<String>? = service.findByPathWithRegex(folder)

            File(folder).walkTopDown()
                    .filter { it.isFile}
                    .filter { !textFileList.contains(it.extension) }
                    .filter{filterBlackListPath(noSearchList, it) }
                 //   .filter { !existList?.contains(it.absolutePath.toString().md5())!! }
                    .forEach {
                        // build the document
                        val dataFile = DataFile(id = it.absolutePath.toString().md5()
                                ,name = it.name.toLowerCase()
                                ,path = it.absolutePath.toLowerCase()
                                ,lines = "" )
                        //  service.insertDataFile(dataFile)
                        DFList.add(dataFile)
                    }

            if (DFList.isNotEmpty()) {
               // service.insertDataFile(DFList.toList())
                repository.saveAll(DFList)
            }

        }

        println("Finish readBinaryfiles...")

    }

    override fun indexTextFiles(folders: List<String>) {

        val textFileList = listOf("txt","sql","java","py","bat","csv","kt","kts")
        val noSearchList = dontSearchList()
        print("Inserting: ")

         folders.parallelStream().forEach { folder ->
        //folders.forEach { folder ->
            println(folder)
            File(folder).walkTopDown()
                    .filter { it.isFile}
                    .filter{filterBlackListPath(noSearchList, it) }
                    .filter { textFileList.contains(it.extension) }
                    .forEach { it ->

                       // println(it.absolutePath)
                        val lines = it.readLines().map { it+"\n" }.flatMap { pepe ->
                            """(\w|[\n]){3,}""".toRegex().findAll(pepe).map { it2 -> it2.value }
                                    .map {it3 -> it3.toLowerCase() }.toList()
                        }.joinToString(" ")

                        val dataFile = DataFile(
                                id = it.path.md5()
                                ,name = it.name.toLowerCase()
                                ,path = it.path.toLowerCase()
                                ,type = "data-file"
                                ,lines = lines
                        )

                        repository.save(dataFile)
                    }
        }

        println("Finish Indexing...")

    }


}