package com.libreria.libreria.domain.usecase.s3;

import com.libreria.libreria.domain.model.s3.S3FileGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.io.InputStream;

@RequiredArgsConstructor
public class S3FileUseCase {

    private final S3FileGateway s3FileGateway;

    public Mono<String> uploadFile(String key, InputStream fileInputStream, long fileSize) {
        return s3FileGateway.uploadFile(key,fileInputStream,fileSize);
    }

    public Mono<InputStream> downloadFile(String key) {
        return s3FileGateway.downloadFile(key);
    }

}
