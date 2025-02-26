package com.historycode.api.models.adminPanel.streetcode;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class StreetcodeRequestBody {
    private int index;
    private int streetcodeType;
    private String title;
    private String firstName;
    private String lastName;
    private String alias;
    private String transliterationUrl;
    private String dateString;
    private Instant eventStartOrPersonBirthDate;
    private Instant eventEndOrPersonDeathDate;
    private List<Object> tags;
    private String teaser;
    private List<Integer> imagesIds;
    private List<ImageDetails> imagesDetails;
    private Object audioId;
    private Object text;
    private List<Object> videos;
    private List<Object> facts;
    private List<Object> timelineItems;
    private List<Object> arts;
    private List<Object> streetcodeArtSlides;
    private List<Object> relatedFigures;
    private List<Object> streetcodeCategoryContents;
    private List<Object> partners;
    private List<Subtitle> subtitles;
    private int viewCount;
    private List<Object> coordinates;
    private List<Object> toponyms;
    private List<Object> statisticRecords;
    private int status;

    public StreetcodeRequestBody setIndex(int index) {
        this.index = index;
        return this;
    }

    public StreetcodeRequestBody setStreetcodeType(int streetcodeType) {
        this.streetcodeType = streetcodeType;
        return this;
    }

    public StreetcodeRequestBody setTitle(String title) {
        this.title = title;
        return this;
    }

    public StreetcodeRequestBody setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StreetcodeRequestBody setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StreetcodeRequestBody setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public StreetcodeRequestBody setTransliterationUrl(String transliterationUrl) {
        this.transliterationUrl = transliterationUrl;
        return this;
    }

    public StreetcodeRequestBody setDateString(String dateString) {
        this.dateString = dateString;
        return this;
    }

    public StreetcodeRequestBody setEventStartOrPersonBirthDate(Instant eventStartOrPersonBirthDate) {
        this.eventStartOrPersonBirthDate = eventStartOrPersonBirthDate;
        return this;
    }

    public StreetcodeRequestBody setEventEndOrPersonDeathDate(Instant eventEndOrPersonDeathDate) {
        this.eventEndOrPersonDeathDate = eventEndOrPersonDeathDate;
        return this;
    }

    public StreetcodeRequestBody setTags(List<Object> tags) {
        this.tags = tags;
        return this;
    }

    public StreetcodeRequestBody setTeaser(String teaser) {
        this.teaser = teaser;
        return this;
    }

    public StreetcodeRequestBody setImagesIds(List<Integer> imagesIds) {
        this.imagesIds = imagesIds;
        return this;
    }

    public StreetcodeRequestBody setImagesDetails(List<ImageDetails> imagesDetails) {
        this.imagesDetails = imagesDetails;
        return this;
    }

    public StreetcodeRequestBody setAudioId(Object audioId) {
        this.audioId = audioId;
        return this;
    }

    public StreetcodeRequestBody setText(Object text) {
        this.text = text;
        return this;
    }

    public StreetcodeRequestBody setVideos(List<Object> videos) {
        this.videos = videos;
        return this;
    }

    public StreetcodeRequestBody setFacts(List<Object> facts) {
        this.facts = facts;
        return this;
    }

    public StreetcodeRequestBody setTimelineItems(List<Object> timelineItems) {
        this.timelineItems = timelineItems;
        return this;
    }

    public StreetcodeRequestBody setArts(List<Object> arts) {
        this.arts = arts;
        return this;
    }

    public StreetcodeRequestBody setStreetcodeArtSlides(List<Object> streetcodeArtSlides) {
        this.streetcodeArtSlides = streetcodeArtSlides;
        return this;
    }

    public StreetcodeRequestBody setRelatedFigures(List<Object> relatedFigures) {
        this.relatedFigures = relatedFigures;
        return this;
    }

    public StreetcodeRequestBody setStreetcodeCategoryContents(List<Object> streetcodeCategoryContents) {
        this.streetcodeCategoryContents = streetcodeCategoryContents;
        return this;
    }

    public StreetcodeRequestBody setPartners(List<Object> partners) {
        this.partners = partners;
        return this;
    }

    public StreetcodeRequestBody setSubtitles(List<Subtitle> subtitles) {
        this.subtitles = subtitles;
        return this;
    }

    public StreetcodeRequestBody setViewCount(int viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public StreetcodeRequestBody setCoordinates(List<Object> coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public StreetcodeRequestBody setToponyms(List<Object> toponyms) {
        this.toponyms = toponyms;
        return this;
    }

    public StreetcodeRequestBody setStatisticRecords(List<Object> statisticRecords) {
        this.statisticRecords = statisticRecords;
        return this;
    }

    public StreetcodeRequestBody setStatus(int status) {
        this.status = status;
        return this;
    }
}
