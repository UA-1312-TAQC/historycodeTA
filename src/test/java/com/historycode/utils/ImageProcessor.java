package com.historycode.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Slf4j
public class ImageProcessor {

    public static String encodeImage(String imagePath){
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(imagePath));
            return Base64.getEncoder().encodeToString(fileContent);
        }catch(IOException ex){
            log.error("Error during image encoding: " + ex.getMessage());
        }
        throw new RuntimeException("Cannot open the file " + imagePath);
    }

    /**
     * This method removes 'data:image/png;base64,' part from image string which was loaded from browser
     */
    public static String clearStringMetadata(String input){
        return  input.substring(input.indexOf(",")+1);
    }

    public static boolean compareEncodedImages(String imageFromFileSystem, String imageFromBrowser){
        return imageFromFileSystem.equals(clearStringMetadata(imageFromBrowser));
    }

    public static boolean compareEncodedAndNormalImage(String pathToImageFromFileSystem, String imageFromBrowser){
        return compareEncodedImages(encodeImage(pathToImageFromFileSystem), clearStringMetadata(imageFromBrowser));
    }
}
