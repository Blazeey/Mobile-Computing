package com.blazeey.sixthexercise;

import java.net.URL;

/**
 * Created by venki on 7/1/18.
 */

public class Item {
    private String title;
    private String description;
    private URL url;

    public Item(String title, String description, URL url) {
        this.title = title;
        this.description = description;
        this.url = url;
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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
