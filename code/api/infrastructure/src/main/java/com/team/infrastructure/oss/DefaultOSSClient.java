package com.team.infrastructure.oss;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.InputStream;

public class DefaultOSSClient implements OSSClient {

    private MinioClient client;

    public DefaultOSSClient(OSSProperties.MinioProperties properties) {
        client = MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();

    }

    @Override
    public void upload(String objectName, InputStream fileStream, String bucketName) throws Exception {
        PutObjectArgs args = PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(fileStream, fileStream.available(), -1).build();
        client.putObject(args);
    }

//    @Override
//    public void upload(InputStream inputStream, String bucketName) {
//        UploadObjectArgs.builder().bucket(bucketName).object().filename().build();
//    }
}
