package com.hamedTech.billing.config;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {

    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Value("${aws.s3.access-key}")
    private  String accessKey;

    @Bean
    public S3Client s3Client(){
        return S3Client
                .builder()
                .region(Region.of("eu-north-1"))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}
