package com.historycode.ui.utils;

import org.openqa.selenium.WebElement;

import java.nio.file.Paths;

public class ImageLoader {

    public void loadImageUsingRelativePath(String imagePath, WebElement fileInputField){
        String absolutePath = Paths.get(imagePath).toAbsolutePath().toString();
        loadImageUsingAbsolutePath(absolutePath, fileInputField);
    }

    public void loadImageUsingAbsolutePath(String imagePath, WebElement fileInputField){
         if (!Paths.get(imagePath).toFile().exists()) {
             throw new IllegalArgumentException("Image file does not exist: " + imagePath);
         }
        fileInputField.sendKeys(imagePath);
    }
}
