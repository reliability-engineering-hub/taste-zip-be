package com.example.tastezip.service;


import com.example.tastezip.api.request.CreateRestaurantRequest;
import com.example.tastezip.api.response.RestaurantResponse;
import com.example.tastezip.model.Restaurant;
import com.example.tastezip.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public Restaurant create(
            CreateRestaurantRequest request
    ){
        // TODO: 레스토랑 고유 값 할당 필요
        // DTO의 존재 의미
        Restaurant restaurant = new Restaurant(request.getName(), request.getAddress(), request.getImage());
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Transactional(readOnly = true)
    public RestaurantResponse get(
            Long restaurantId
    ){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("없는 레스토랑입니다."));

        RestaurantResponse restaurantResponse = new RestaurantResponse(
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getImage()
        );

        return restaurantResponse;
    }

    @Transactional
    public void update(
            Long restaurantId,
            CreateRestaurantRequest request
    ){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("없는 레스토랑입니다."));

        restaurant.changeRestaurantInfo(request.getName(), request.getAddress(), request.getImage());
        restaurantRepository.save(restaurant);
    }

    @Transactional
    public void delete(
            Long restaurantId
    ){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("없는 레스토랑입니다."));

        restaurantRepository.deleteById(restaurantId);
    }
}
