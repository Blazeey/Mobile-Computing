package com.blazeey.sixthexercise;

import java.net.URI;

/**
 * Created by venki on 7/1/18.
 */

public class Item {
    private String title;
    private String description;
    private URI uri;

    public Item(String title, String description, URI uri) {
        this.title = title;
        this.description = description;
        this.uri = uri;
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

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

}
