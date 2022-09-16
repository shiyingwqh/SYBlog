package com.wuqihang.syblog.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    boolean upload(MultipartFile file);
    void delete(String path);

    List<String> getAllFilePath();

    File getFileWithPath(String path);
}
