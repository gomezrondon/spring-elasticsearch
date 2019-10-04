package com.gomezrondon.springslasticsearch.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoadFileServiceImp implements LoadFileService {

    private static Flux<String> fromPath(Path path) {
        return Flux.using(() -> Files.lines(path, StandardCharsets.UTF_8), //"UTF-8" "Cp1250"
                Flux::fromStream,
                BaseStream::close
        );
    }


    private static List<Path> getPathOfFilesInFolder(String folder) throws IOException {

        try (Stream<Path> paths = Files.walk(Paths.get(folder))) {
            return paths
                    .filter(Files::isRegularFile)
                    //.peek(System.out::println)
                    .collect(Collectors.toList());
        }
    }


/*     public String readFile(String file) {

          if(file != null){
              Path path = null;
              try {
                  path = getPathOfFilesInFolder(file).get(0);
              } catch (IOException e) {
                  e.printStackTrace();
              }
           //   System.out.println(path.toFile().getName());
              String block = fromPath(path).collect(Collectors.joining("\n")).block();
              return block;
         }

         return "";
     }*/

    public List<String> readFile(String file)  {

        if(file != null){
            List<String> block = fromPath(Paths.get(file)).map(x -> x + "\n").collect(Collectors.toList()).block();
            return block;
        }

        return null;
    }

/*    public List<String> readFile(String file) {

        if(file != null){
            Path path = null;
            try {
                path = getPathOfFilesInFolder(file).get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //   System.out.println(path.toFile().getName());
            List<String> list =  fromPath(path).map(x -> x+"\n").collect(Collectors.toList()).block();
            return list;
        }

        return new ArrayList<>();
    }*/

}
