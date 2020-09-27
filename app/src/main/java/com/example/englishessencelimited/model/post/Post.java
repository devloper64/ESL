package com.example.englishessencelimited.model.post;

public class Post {

    private String deviceId;
    private String description;
    private String image;

    public Post(){

    }

    public Post(String deviceId,  String description, String image) {

        this.deviceId = deviceId;
        this.description = description;
        this.image=image;
    }



    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
