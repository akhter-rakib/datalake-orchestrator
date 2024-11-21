package com.rakib.datalakeorchestrator.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AzureBlobService implements IAzureBlobService {

    private static final Logger logger = Logger.getLogger(AzureBlobService.class.getName());
    private final BlobContainerClient containerClient;

    public AzureBlobService(@Value("${azure.storage.account-name}") String accountName,
                            @Value("${azure.storage.account-key}") String accountKey,
                            @Value("${azure.storage.container-name}") String containerName) {
        String connectionString = String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
                accountName, accountKey);
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        containerClient = blobServiceClient.getBlobContainerClient(containerName);

        if (!containerClient.exists()) {
            containerClient.create();
        }
    }

    @Override
    public String uploadFile(InputStream fileStream, String fileName) throws IOException {
        String blobName = "raw/" + UUID.randomUUID() + "-" + fileName;
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        try (fileStream) {
            blobClient.upload(fileStream, fileStream.available(), true);
            logger.info("File uploaded successfully to: " + blobClient.getBlobUrl());
            return blobClient.getBlobUrl();
        } catch (Exception e) {
            logger.severe("Failed to upload file: " + e.getMessage());
            throw new IOException("Error uploading file to Azure Blob Storage", e);
        }
    }
}
