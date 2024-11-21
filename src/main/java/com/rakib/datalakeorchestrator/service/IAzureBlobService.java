package com.rakib.datalakeorchestrator.service;


import java.io.IOException;
import java.io.InputStream;

public interface IAzureBlobService {
    /**
     * Uploads a file to Azure Blob Storage.
     *
     * @param fileStream The input stream of the file to be uploaded.
     * @param fileName   The name of the file to be uploaded.
     * @return The URL of the uploaded blob.
     */
    String uploadFile(InputStream fileStream, String fileName) throws IOException;
}