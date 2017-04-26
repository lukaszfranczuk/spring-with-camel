package com.backbase.atm;

import com.backbase.atm.model.RouteHeader;

import java.util.HashMap;
import java.util.Map;

public class CamelMessageHeadersMapBuilder {
    private Map<String, Object> headers;

    public CamelMessageHeadersMapBuilder() {
        headers = new HashMap<>();
    }

    public CamelMessageHeadersMapBuilder withCityName(String cityName) {
        headers.put(RouteHeader.CITY_NAME.getName(), cityName);
        return this;
    }

    public Map<String, Object> build() {
        return headers;
    }
}
