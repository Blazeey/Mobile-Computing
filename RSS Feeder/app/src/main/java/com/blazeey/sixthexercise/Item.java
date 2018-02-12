package com.blazeey.sixthexercise;


/**
 * Created by t4 on 1/30/2018.
 */
public class Item {
    private String title;
    private String description;
    private String link;

    public Item() {
    }

    public Item(String title, String description, String String) {
        this.title = title;
        this.description = description;
        this.link = String;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String toString(){
        return title+","+link+","+description;
    }
}
