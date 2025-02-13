package com.historycode.api.dataProviders.enums;

import lombok.Getter;

@Getter
public enum Status {
    DRAFT(0, "Draft", "Status:Draft"),
    PUBLISHED(1, "Published", "Status:Published"),
    DELETED(2, "Deleted", "Status:Deleted");
    private final int code;
    private final String name;
    private final String queryParameter;

    Status(int code, String name, String queryParameter) {
        this.code = code;
        this.name = name;
        this.queryParameter = queryParameter;
    }
}
