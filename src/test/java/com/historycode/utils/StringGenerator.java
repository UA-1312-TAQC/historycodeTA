package com.historycode.utils;

import java.util.Random;

public class StringGenerator {


    public static String generateUserData(int nameLength, int surnameLength){
        return  generateString(nameLength) + " " + generateString(surnameLength);
    }

    public static String generateString(int length){
        return generateString(length, 97, 122);
    }

    public static String generateString(int length, int leftLimit , int rightLimit){
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
