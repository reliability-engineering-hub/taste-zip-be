package com.example.tastezip.service;


import com.example.tastezip.api.request.CreateRestaurantRequest;
import com.example.tastezip.model.Restaurant;
import com.example.tastezip.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(
            CreateRestaurantRequest request
    ){
        // TODO: 레스토랑 고유 값 할당 필요
        // DTO의 존재 의미
        Restaurant restaurant = new Restaurant(request.getName(), request.getAddress(), request.getImage());
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
