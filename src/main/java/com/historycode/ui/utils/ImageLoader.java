package com.historycode.ui.utils;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageLoader {

    private static final String BASE_PATH = "src/test/resources/";
    private static final Logger logger = Logger.getLogger(ImageLoader.class.getName());

    public static void loadImageUsingRelativePath(String imageFileName, WebElement fileInputField){
        String absolutePath = Paths.get(BASE_PATH + imageFileName).toAbsolutePath().toString();
        loadImageUsingAbsolutePath(absolutePath, fileInputField);
    }

    public static void loadImageUsingAbsolutePath(String imagePath, WebElement fileInputField){
         if (!Paths.get(imagePath).toFile().exists()) {
             throw new IllegalArgumentException("Image file does not exist: " + imagePath);
         }
        fileInputField.sendKeys(imagePath);
    }

    public static String getBase64FromFile(String resourcePath) {
        if (resourcePath == null || resourcePath.trim().isEmpty()) {
            logger.log(Level.WARNING, "Resource path cannot be null or empty");
            return null;
            }
        resourcePath = resourcePath.trim();
        File file = new File(BASE_PATH + resourcePath);
        if (!file.exists()) {
            logger.log(Level.WARNING, "File not found: {0}", resourcePath);
            return null;
        }
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + resourcePath, e);
            return null;
        }
    }

    private static String clearStringMetadata(String input){
        return  input.substring(input.indexOf(",")+1);
    }
}
