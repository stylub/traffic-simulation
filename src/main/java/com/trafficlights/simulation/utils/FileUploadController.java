package com.trafficlights.simulation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {
    private final ObjectMapper objectMapper;

    public FileUploadController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/upload")
    public CommandWrapper uploadJson(@RequestParam("file") MultipartFile file) throws IOException {
        // Parse the uploaded file into a CommandWrapper object
        return objectMapper.readValue(file.getInputStream(), CommandWrapper.class);
    }
}