package com.jsontoxml.converter.model;

import java.util.List;

public class LiquibaseRequestDTO {
    private String author;
    private List<ChangeSetsItem> changeSets;
    private int id;

    public String getAuthor() {
        return author;
    }

    public List<ChangeSetsItem> getChangeSets() {
        return changeSets;
    }

    public int getId() {
        return id;
    }
}