package ru.link.experimental.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileProperty {
    private String uploadDirectory;

    public String getUploadDirectory(){
        return uploadDirectory;
    }

    public void setUploadDirectory(String uploadDirectory){
        this.uploadDirectory = uploadDirectory;
    }
}
