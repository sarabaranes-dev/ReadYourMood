package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {

    @Value("${upload.dir}")
    private String uploadDir;

    // quando il browser chiede /uploads/nomeFile.jpg, questo metodo lo serve
    @GetMapping("/uploads/{nomeFile:.+}")
    public ResponseEntity<byte[]> serveImage(@PathVariable String nomeFile) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(nomeFile);
        if (!Files.exists(filePath)) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = Files.readAllBytes(filePath);
        String contentType = Files.probeContentType(filePath);
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType != null ? contentType : "image/jpeg"))
            .body(imageBytes);
    }
}