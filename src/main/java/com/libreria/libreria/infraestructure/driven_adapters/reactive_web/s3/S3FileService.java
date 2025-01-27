package com.libreria.libreria.infraestructure.driven_adapters.reactive_web.s3;

import com.libreria.libreria.domain.model.s3.S3FileGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3FileService implements S3FileGateway {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;


    @Override
    public Mono<String> uploadFile(String key, InputStream fileInputStream, long fileSize) {
        return Mono.fromRunnable(() -> {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    RequestBody.fromInputStream(fileInputStream, fileSize)
            );
        }).then(Mono.just("Archivo subido correctamente con key: " + key));
    }

    @Override
    public Mono<InputStream> downloadFile(String key) {
        return Mono.fromCallable(() -> {
            ResponseInputStream<GetObjectResponse> object = s3Client.getObject(
                    GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build()
            );
            return object;
        });
    }

}
