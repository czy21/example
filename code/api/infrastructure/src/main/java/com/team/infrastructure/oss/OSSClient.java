package com.team.infrastructure.oss;

import java.io.InputStream;
import java.util.Map;

public interface OSSClient {

    void put(String objectName, InputStream fileStream, String bucketName) throws Exception;

    void put(String objectName, InputStream fileStream, String bucketName, Map<String, String> tags) throws Exception;

    InputStream get(String objectName, String bucketName) throws Exception;
}
