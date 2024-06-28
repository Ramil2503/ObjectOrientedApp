package org.example.webclient.model.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google.cloud.credentials")
public class GoogleCloudProperties {

    private String filePath;

}
