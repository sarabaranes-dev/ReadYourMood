package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${upload.dir}")
    private String uploadDir;

    // salva il file su disco e restituisce il nome del file salvato
    public String salva(MultipartFile file) throws IOException {
        // crea la cartella se non esiste
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // genera un nome univoco per evitare conflitti
        String nomeFile = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(nomeFile);
        Files.copy(file.getInputStream(), filePath);

        return nomeFile;
    }

    // elimina il file dal disco
    public void elimina(String nomeFile) throws IOException {
        if (nomeFile != null && !nomeFile.isEmpty()) {
            Path filePath = Paths.get(uploadDir).resolve(nomeFile);
            Files.deleteIfExists(filePath);
        }
    }
}