package com.example.myrh.Service.Impl;

import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Repository.FileRepository;
import com.example.myrh.Service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
@Transactional
public class FileServiceImpl implements IFileService {

    private final FileRepository fileRepository ;
    private final String FILE_PATH = "C:/Users/adm/Desktop/File/";

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileEntity storeFile(MultipartFile file) throws Exception {
        String filePath = FILE_PATH + file.getOriginalFilename();
        FileEntity fileBuild =  FileEntity.builder().
                    name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .fileData(file.getBytes())
                    .path(filePath)
                    .build();
       fileBuild = fileRepository.save(fileBuild);
       file.transferTo(new File(filePath));
       if (fileBuild.getId() != null){
           return fileBuild;
       }
        return null ;
    }

    @Override
    public byte[] getFiles(String fileName) {
        return fileRepository.findByName(fileName).get().getFileData();
    }

    @Override
    public String storeDataIntoFileSystem(MultipartFile file) {
        return null;
    }

    @Override
    public byte[] downloadFilesFromFileSystem(String fileName) {
      //  String path = fileRepository.findByName(fileName).get().getPath();

     //   return java.nio.file.Files.readAllBytes(new File(path).toPath());
        return new byte[0];
    }
}
