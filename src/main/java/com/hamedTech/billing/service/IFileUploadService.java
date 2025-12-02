package com.hamedTech.billing.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {


    String uploadFile(MultipartFile file);

    boolean deleteFile(String imgUrl);
}
