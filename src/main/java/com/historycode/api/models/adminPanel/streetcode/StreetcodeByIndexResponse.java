package com.historycode.api.models.adminPanel.streetcode;

import com.historycode.api.models.tag.TagResponseExtended;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class StreetcodeByIndexResponse {
    private String firstName;
    private String rank;
    private String lastName;
    private int id;
    private int index;
    private String title;
    private String dateString;
    private String alias;
    private String transliterationUrl;
    private int status;
    private Date eventStartOrPersonBirthDate;
    private Date eventEndOrPersonDeathDate;
    private int viewCount;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private List<TagResponseExtended> tags;
    private String teaser;
    private int streetcodeType;
}
