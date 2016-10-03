package com.world.movies.android.app.flicknet.model;

import java.io.Serializable;

/**
 * Created by eltonjhony on 25/09/16.
 */

public class TrailerItem implements Serializable {

    private String name;
    private String key;
    private String thumbnail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void buildThumbnail() {
        setThumbnail("http://img.youtube.com/vi/" + this.getKey() + "/0.jpg");
    }
}
