package com.lifetime.layout_practice4;

public class Info {
    private int image;
    private String description;
    private String time;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Info(int image, String description, String time) {
        this.image = image;
        this.description = description;
        this.time = time;
    }
}
