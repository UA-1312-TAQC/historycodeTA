package com.historycode.cucumber.steps;

import io.cucumber.java.en.And;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateEditNewsModalSteps extends NewsPageSteps {


    @And("I fill in the {string} field with {string}")
    public void iFillInTheFieldWith(String field, String value) {
        switch (field) {
            case "Title" -> createEditNewsModal.inputNewsTitle(value);
            case "Link" -> createEditNewsModal.inputNewsLinkTranslit(value);
            case "Text" -> createEditNewsModal.inputNewsTextEditor(value);
            case "Image" -> createEditNewsModal.clickUploadNewsPhoto(value);
            case "Date"-> {
                Date date;

                if (value.equals("current date")) {
                    date = new Date(System.currentTimeMillis());
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

                    try {
                        date = formatter.parse(value);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }

                createEditNewsModal.inputNewsCreationDate(date);
            }
        }
    }
}
