package com.historycode.ui.data_provider.enums;

import lombok.Getter;

import java.util.List;


@Getter
public enum SocialMedia {
    X("X", "https://x.com/"),
    INSTAGRAM("Instagram", "https://www.instagram.com/"),
    FACEBOOK("Facebook", "https://www.facebook.com/"),
    YOUTUBE("Youtube", "https://www.youtube.com/"),
    LINKEDIN("LinkedIn", "https://ua.linkedin.com/"),
    TIKTOK("TikTok", "https://www.tiktok.com/"),
    BEHANCE("Behance", "https://www.behance.net/"),
    YOUR_SITE("Ваш сайт", "https://google.com/");
    private final String name;

    private final String validLink;

    private final List<String> invalidLinks;

    SocialMedia(String name, String validLink) {
        this.name = name;
        this.validLink = validLink;
        invalidLinks = null;
    }

    SocialMedia(String name, String validLink, List<String> invalidLinks) {
        this.name = name;
        this.validLink = validLink;
        this.invalidLinks = invalidLinks;
    }


    public static SocialMedia getSocialMediaById(String targetName){
        for(SocialMedia media : values()) {
            if (media.name.equals(targetName)){
                return media;
            }
        }
        return null;
    }
}
