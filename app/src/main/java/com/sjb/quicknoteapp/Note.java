package com.sjb.quicknoteapp;

public class Note {
    private String id;
    private final String title;
    private final String discreption;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDiscreption() {
        return discreption;
    }

    public Note(String id, String title, String discreption) {
        this.id = id;
        this.title = title;
        this.discreption = discreption;
    }
}
