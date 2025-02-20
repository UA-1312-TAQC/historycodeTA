package com.historycode.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CustomStringGenerator {
    public static String generateUserLastFirstName(int nameLength, int surnameLength){
        return RandomStringUtils.randomAlphabetic(nameLength) + " " + RandomStringUtils.randomAlphabetic(surnameLength);
    }
}
