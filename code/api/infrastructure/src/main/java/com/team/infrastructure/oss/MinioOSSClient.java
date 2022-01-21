package com.team.infrastructure.oss;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.InputStream;

public class MinioOSSClient implements OSSClient {

    private final MinioClient client;

    public MinioOSSClient(OSSProperties.MinioProperties properties) {
        client = MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }

    @Override
    public void put(String objectName, InputStream fileStream, String bucketName) throws Exception {
        PutObjectArgs args = PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(fileStream, fileStream.available(), -1).build();
        client.putObject(args);
    }

    @Override
    public InputStream get(String objectName, String bucketName) throws Exception {
        GetObjectArgs args = GetObjectArgs.builder().bucket(bucketName).object(objectName).build();
        return client.getObject(args);
    }
}
