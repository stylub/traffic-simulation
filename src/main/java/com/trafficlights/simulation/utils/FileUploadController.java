package com.trafficlights.simulation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class FileUploadController {
    static class CommandWrapper {
        private List<Command> commands;

        public List<Command> getCommands() {
            return commands;
        }
    }
    private final ObjectMapper objectMapper;

    public FileUploadController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private CommandWrapper parseJson(InputStream src) throws IOException {
        return objectMapper.readValue(src, CommandWrapper.class);
    }
    @PostMapping("/upload")
    public List<Command> uploadJson(@RequestParam("file") MultipartFile file) throws IOException {
        CommandWrapper wrapper = parseJson(file.getInputStream());
        return wrapper.getCommands();
    }
}