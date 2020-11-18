package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.link.experimental.Entities.FileEntity;
import ru.link.experimental.Properties.FileProperty;
import ru.link.experimental.Repositories.FileRepository;
import ru.link.experimental.Services.PurchaseServices.FileServiceInterface;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class FileService implements FileServiceInterface {

    @Autowired
    private FileRepository fileRepository;

    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileProperty fileProperty) throws IOException {
        this.fileStorageLocation = Paths.get(fileProperty.getUploadDirectory()).toAbsolutePath().normalize();
        if (!Files.exists(fileStorageLocation)){
            Files.createDirectory(fileStorageLocation);
        }
    }

    @Override
    public void addFiles(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException, IOException {
        for (MultipartFile file : multipartFiles){
            createFile(file);
        }
    }

    @Override
    public Resource getFile(UUID id) throws MalformedURLException {
        FileEntity fileEntity = fileRepository.getOne(id);

        String extension = fileEntity.getName().substring(fileEntity.getName().lastIndexOf("."));
        Path filePath = this.fileStorageLocation.resolve(fileEntity.getHash() + extension).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        return resource;
    }

    private void createFile(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setMimeType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setHash();

        fileRepository.save(fileEntity);

        String extension = fileEntity.getName().substring(fileEntity.getName().lastIndexOf("."));
        storeFile(file, fileEntity.getHash(), extension);
    }

    private void storeFile(MultipartFile file, String hash, String fileExtension) throws IOException {
        Path targetLocation = this.fileStorageLocation.resolve(hash + fileExtension);
        Files.copy(file.getInputStream(), targetLocation);
    }

}
