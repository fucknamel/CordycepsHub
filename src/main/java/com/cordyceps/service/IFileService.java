package com.cordyceps.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IFileService {

    String upload(MultipartFile file, String path);

    public String uploadLocalFile(File file, String path);
}
