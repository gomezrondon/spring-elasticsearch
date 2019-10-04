package com.gomezrondon.springslasticsearch.service

import com.gomezrondon.springslasticsearch.utils.loadFolders
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*


@Component
class ScheduledTasks(private val indexService: IndexService) {

    private val log: Logger = LoggerFactory.getLogger(ScheduledTasks::class.java)

    private val dateFormat = SimpleDateFormat("HH:mm:ss")

    @Value("\${schedule.enable:false}")
    private val enableJobs:Boolean = false

    @Scheduled(fixedRate = 30000) //30 sec
    fun indexFilesByNameJob() {
        if (enableJobs) {
            log.info("Executing IndexByName Service {}", dateFormat.format(Date()))
            val folders = loadFolders()
            indexService.indexFilesByName(folders)
            log.info("Finished IndexByName Service {}", dateFormat.format(Date()))
        }

    }

    @Scheduled(fixedRate = 300000,initialDelay = 20) // 5 min
    fun indexTextFilesJob() {
        if (enableJobs) {
            log.info("Executing IndexTextFile Service {}", dateFormat.format(Date()))
            val folders = loadFolders()
            indexService.indexTextFiles(folders)
            log.info("Finished IndexTextFile Service {}", dateFormat.format(Date()))
        }
    }

}