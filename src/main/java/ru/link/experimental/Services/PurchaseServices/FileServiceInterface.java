package ru.link.experimental.Services.PurchaseServices;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.link.experimental.Entities.FileEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public interface FileServiceInterface {

    void addFiles(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException, IOException;

    Resource getFile(UUID id) throws MalformedURLException;

}
