package com.example.springbootsample.application.service;

import com.example.springbootsample.domain.user.model.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class UserApplicationService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ResourceLoader resourceLoader;

    // File Directory
    private String filePath = "/Users/ginsenglee";

    // Path Delimiter
    private static final String separator = File.separator;

    /** Generate a gender Map */
    public Map<String, Integer> getGenderMap(Locale locale) {
        Map<String, Integer> genderMap = new LinkedHashMap<>();

        String male = messageSource.getMessage("male", null, locale);
        String female = messageSource.getMessage("female", null, locale);

        genderMap.put(male, 1);
        genderMap.put(female, 2);

        return genderMap;
    }

    /** Save User List to CSV */
    public void saveUserCSV(List<MUser> userList, String fileName) throws IOException {
        // CSV string creation
        StringBuilder stringBuilder = new StringBuilder();

        for (MUser user : userList) {
            stringBuilder.append(user.toCSV());
        }

        // Get byte array
        byte[] bytes = stringBuilder.toString().getBytes();

        // Set the saving file destination path
        Path path = Paths.get(filePath + separator + fileName);

        // Write up the file
        Files.write(path, bytes);
    }

    /** Get CSV File */
    public byte[] getCSV(String fileName) throws IOException {
        // Get file path
        String path = "file:" + filePath + separator + fileName;

        // Get file
        Resource resource = resourceLoader.getResource(path);
        File file = resource.getFile();

        return Files.readAllBytes(file.toPath());
    }
}