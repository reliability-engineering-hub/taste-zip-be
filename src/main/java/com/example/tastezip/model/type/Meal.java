package com.example.tastezip.model.type;

public enum Meal {
    BREAKFAST(1),
    LUNCH(2),
    DINNER(3);

    private Integer meal;

    Meal(Integer meal) {
        this.meal = meal;
    }

    public Integer getMeal(){
        return meal;
    }

    public static Meal fromInteger(Integer value) {
        for (Meal m : Meal.values()){
            if (m.meal.equals(value)){
                return m;
            }
        }
        throw new IllegalArgumentException("Unknown meal value : " + value);
    }
}
