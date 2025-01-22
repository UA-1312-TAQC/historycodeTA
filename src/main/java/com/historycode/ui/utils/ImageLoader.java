package com.historycode.ui.utils;

import org.openqa.selenium.WebElement;

import java.nio.file.Paths;

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
}
