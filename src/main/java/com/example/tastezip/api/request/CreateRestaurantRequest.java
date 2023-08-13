package com.example.tastezip.api.request;

public class CreateRestaurantRequest {
    private final String name;
    private final String address;
    private final String image;

    public CreateRestaurantRequest(String name, String address, String image){
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public String getImage(){
        return this.image;
    }
}
