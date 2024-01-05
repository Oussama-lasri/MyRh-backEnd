package com.example.myrh.Controller;

import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Repository.FileRepository;
import com.example.myrh.Service.IFileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class FileController {

    private final IFileService fileService ;

    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }
    @PostMapping("/uploadFileInToDB")
    public ResponseEntity<?> storeFileToDB(@RequestParam("file")MultipartFile file) throws Exception{
        FileEntity fileSaved = fileService.storeFile(file);
        return  ResponseEntity.ok().body(fileSaved);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable String fileName){
        byte[] file = fileService.getFiles(fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/pdf")).body(file);
    }
    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> getFileImage(@PathVariable String fileName){
        byte[] file = fileService.getFiles(fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(file);
    }


}
