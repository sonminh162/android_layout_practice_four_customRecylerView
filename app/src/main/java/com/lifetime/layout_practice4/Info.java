package com.lifetime.layout_practice4;

public class Info {
    private int image;
    private String description;
    private String time;
    private boolean isChecked;
    private boolean liked;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

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

    public Info(int image, String description, String time, boolean isChecked, boolean liked) {
        this.image = image;
        this.description = description;
        this.time = time;
        this.isChecked = isChecked;
        this.liked = liked;
    }
}
