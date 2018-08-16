package com.gft.enums;

public enum DishType {

    EGGS(1, "morning", "eggs"),
    TOAST(2, "morning", "toast"),
    COFFEE(3, "morning", "coffee"),
    ERROR(4, "morning", "error"),

    STEAK(1, "night", "steak"),
    POTATO(2, "night", "potato"),
    WINE(3, "night", "wine"),
    CAKE(4, "night", "cake");

    private Integer order;

    private String timeOfDay;

    private String dish;

    DishType(Integer order, String timeOfDay, String dish) {
        this.order = order;
        this.timeOfDay = timeOfDay;
        this.dish = dish;
    }

    public static String getDishByOrderAndTimeOfDay(int order, String timeOfDay) {
        for (DishType type : DishType.values()) {
            if(order == type.getOrder() && timeOfDay.equalsIgnoreCase(type.getTimeOfDay())) {
                return type.getDish();
            }
        }
        return null;
    }

    public int getOrder() {
        return order;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getDish() {
        return dish;
    }
}
