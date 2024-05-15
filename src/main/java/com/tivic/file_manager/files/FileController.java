package com.tivic.file_manager.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("")
    public ResponseEntity<String> hello(){
        System.out.println("hello world");
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileStringMessage = new String(file.getBytes(), StandardCharsets.UTF_8);
            this.fileService.sendMessage("file-topic", fileStringMessage);
            return new ResponseEntity<>("Mensagem enviada para o kafka", HttpStatus.OK);
        }catch(IOException e){
            return new ResponseEntity<>("Não foi possível converter o arquivo para bytes. Detalhes: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
