package org.example.webclient.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ImageService {

    private final Storage storage;
    private final String bucketName = "image-service-abracadabra";

    public ImageService() {
        // Initialize the Storage client with the service account credentials
        try {
            Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("/etc/secrets/key.json"));
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveImage(MultipartFile image, Long productId) {
        try {
            String blobName = "images/" + productId + ".jpg";
            BlobId blobId = BlobId.of(bucketName, blobName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteImage(Long productId) {
        String blobName = "images/" + productId + ".jpg";
        BlobId blobId = BlobId.of(bucketName, blobName);
        storage.delete(blobId);
    }
}
