package ru.link.experimental.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.link.experimental.Services.PurchaseServices.Implements.FileService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file/upload")
    public void addFile(@RequestParam(value = "files")MultipartFile[] files) throws IOException, NoSuchAlgorithmException {
        fileService.addFiles(files);
    }

    @GetMapping("/file/download/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable("fileId") UUID fileId, HttpServletRequest request) throws IOException {
        Resource resource = fileService.getFile(fileId);
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        System.out.println(contentType);
        System.out.println(resource.getFile().getAbsolutePath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
