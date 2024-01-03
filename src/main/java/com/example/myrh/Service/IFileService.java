package com.example.myrh.Service;

import com.example.myrh.Entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    public FileEntity storeFile(MultipartFile file) throws Exception;

    public byte[] getFiles(String fileName);

    public String storeDataIntoFileSystem(MultipartFile file);
    public byte[] downloadFilesFromFileSystem(String fileName);
}
