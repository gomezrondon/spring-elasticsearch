package com.gomezrondon.springslasticsearch


/*        val folders = loadFolders()
        indexService.indexFilesByName(folders)
        indexService.indexTextFiles(folders)*/
//---------------------------------------------------------
// repo.findByName("*javier gomez*").map { it.path }.forEach { println(it) } //14 hits funcion
//repo.findByNameCustomQuery("javier gomez", PageRequest(0,500)).map { it.path }.forEach { println(it) } // 22 hits
// repo.findByLines("prc*").map { it.path }.forEach { println(it) } //  funciona

// repo.findByLines("javier gomez").map { it.path }.forEach { println(it) } //  18 hits
// repo.findByLines("*javier gomez*").map { it.path }.forEach { println(it) } // 26 hits works better

//    repo.findByLinesUsingCustomQuery("javier gomez", PageRequest(0,500)).map { it.path }.forEach { println(it) } // 40 hits definitivamente funciona

/*        repo.findByNameUsingCustomQuery("*javier*").forEach { println(it) } //funciona
        println(">>>>>>>>>>>>>>>>>> 1 >>")
        repo.findByNameUsingRegex(".*javier gomez.*").map { it.path }.forEach { println(it) } //funciona
        println(">>>>>>>>>>>>>>>>>> 2 >>")
        searchService.searchByFileName("*javier gomez*").forEach { println(it) }
        println(">>>>>>>>>>>>>>>>>> 3 >>")
        repo.findByLinesUsingRegex(".*javier gomez.*").map { it.path }.forEach { println(it) } //funciona
        println(">>>>>>>>>>>>>>>>>> fin >>")*/
