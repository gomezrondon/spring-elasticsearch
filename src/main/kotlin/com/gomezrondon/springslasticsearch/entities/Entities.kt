package com.gomezrondon.springkotlin.entities

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.io.File


data class Paquete(val file: File, var lines:List<String> )



@Document(indexName = "documentx", type = "data-file")
data class DataFile(
                    @Id
                    var id:String=""
                    ,var name:String = "NO-NAME"
                    ,var type:String = "data-file"
                    ,var path:String=""
                    ,var lines:String="")


