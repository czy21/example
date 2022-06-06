package com.team.infrastructure.oss;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.apache.commons.collections4.MapUtils;

import java.io.InputStream;
import java.util.Map;

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
        put(objectName, fileStream, bucketName, null);
    }

    @Override
    public void put(String objectName, InputStream fileStream, String bucketName, Map<String, String> tags) throws Exception {
        PutObjectArgs.Builder argsBuilder = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(fileStream, fileStream.available(), -1);
        if (MapUtils.isNotEmpty(tags)) {
            argsBuilder.tags(tags);
        }
        client.putObject(argsBuilder.build());
    }

    @Override
    public InputStream get(String objectName, String bucketName) throws Exception {
        GetObjectArgs args = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        return client.getObject(args);
    }
}
