package com.backbase.recruitment.model;

public enum RouteHeader {
    CITY_NAME("cityName");

    private static final String LEFT_CURLY_BRACKET = "{";
    private static final String RIGHT_CURLY_BRACKET = "}";
    private String name;

    RouteHeader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getRouteRepresentation() {
        return LEFT_CURLY_BRACKET + name + RIGHT_CURLY_BRACKET;
    }
}
