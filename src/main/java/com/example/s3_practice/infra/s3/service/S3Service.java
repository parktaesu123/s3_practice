package com.example.s3_practice.infra.s3.service;

import com.example.s3_practice.infra.s3.exception.DeleteImageFailedException;
import com.example.s3_practice.infra.s3.exception.FileFailedException;
import com.example.s3_practice.infra.s3.exception.ImageNotFoundException;
import com.example.s3_practice.infra.s3.exception.IncorrectUrlFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final S3AsyncClient s3AsyncClient;

    private final Set<String> IMAGE_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif");


    public CompletableFuture<String> uploadAsync(MultipartFile file) {

        String s3FileName = file.getOriginalFilename();
        validate(s3FileName);

        String key = UUID.randomUUID() + "." + getExtension(s3FileName);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType())
                .build();

        try {
            CompletableFuture<PutObjectResponse> upload = s3AsyncClient.putObject(
                    request,
                    AsyncRequestBody.fromBytes(file.getBytes())
            );

            return upload.thenApply(response -> getUrl(key))
                    .exceptionally(ex -> {
                        throw FileFailedException.EXCEPTION;
                    });

        } catch (IOException e) {
            throw FileFailedException.EXCEPTION;
        }
    }

    public CompletableFuture<Void> deleteAsync(String imageUrl) {
        try {
            URL url = new URI(imageUrl).toURL();
            String decodedKey = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
            String key = decodedKey.startsWith("/") ? decodedKey.substring(1) : decodedKey;

            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            return s3AsyncClient.deleteObject(deleteObjectRequest)
                    .thenApply(response -> null);

        } catch (URISyntaxException | MalformedURLException e) {
            throw IncorrectUrlFormatException.EXCEPTION;
        } catch(IOException e) {
            throw DeleteImageFailedException.EXCEPTION;
        }
    }

    private void validate(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw ImageNotFoundException.EXCEPTION;
        }

        String extension = getExtension(fileName);
        if (!IMAGE_EXTENSIONS.contains(extension)) {
            throw FileFailedException.EXCEPTION;
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    private String getUrl(String fileName) {
        return "https://" + bucket + ".s3.amazonaws.com/" + fileName;
    }

}
