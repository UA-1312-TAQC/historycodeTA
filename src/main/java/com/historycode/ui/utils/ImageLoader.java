package com.historycode.ui.utils;

import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageLoader {

    static final String BASE_PATH = "src/test/resources/";

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
        File file = new File(BASE_PATH + resourcePath);
        if (!file.exists()) {
            return ("Файл не знайдено: " + resourcePath);
        }
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Помилка читання файлу: ", e);
        }
    }
}
