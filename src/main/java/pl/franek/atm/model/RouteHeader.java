package pl.franek.atm.model;

import java.text.MessageFormat;

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
        return MessageFormat.format("'{'{0}'}'", name);
    }
}
