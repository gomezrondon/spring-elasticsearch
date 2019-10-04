package com.gomezrondon.springslasticsearch

import com.gomezrondon.springslasticsearch.repository.DataFileRepository
import com.gomezrondon.springslasticsearch.service.IndexService
import com.gomezrondon.springslasticsearch.service.SearchService
import com.gomezrondon.springslasticsearch.utils.loadFolders
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.EnableScheduling
import java.io.File

@SpringBootApplication
@EnableScheduling
class Application(private val repo: DataFileRepository): CommandLineRunner {

    override fun run(vararg args: String?) {
        val count = repo.count()
        println(">>>>>>>>>>> count >>>>>>> $count >>")
    }
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
