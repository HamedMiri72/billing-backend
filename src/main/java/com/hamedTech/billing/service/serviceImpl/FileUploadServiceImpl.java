package com.hamedTech.billing.service.serviceImpl;

import com.hamedTech.billing.service.IFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectAclResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements IFileUploadService {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    @Override
    public String uploadFile(MultipartFile file) {

        String fileExtention = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        String fileName = UUID.randomUUID().toString() + "." + fileExtention;

        try {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .acl("public-read")
                    .contentType(file.getContentType())
                    .build();

            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            if (response.sdkHttpResponse().isSuccessful()) {
                return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "response for uploading image is nor seccessful");
            }

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Exception occured while uploading file in s3 buket");
        }


    }

    @Override
    public boolean deleteFile(String imgUrl) {
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
        return true;
    }


}
