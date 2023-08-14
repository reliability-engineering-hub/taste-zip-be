package com.example.tastezip.api.response;

public class RestaurantResponse {
    private final String name;
    private final String address;
    private final String image;

    public RestaurantResponse(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
