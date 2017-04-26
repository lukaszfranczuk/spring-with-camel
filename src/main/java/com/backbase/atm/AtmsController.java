package com.backbase.atm;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AtmsController {

    private static final String EMPTY_CITY_NAME_VALIDATION_MESSAGE = "Required query parameter 'city' can't be empty";

    @Produce(uri = "direct:atms")
    private ProducerTemplate template;


    @RequestMapping(path = "/atms", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getAtmsFromExternalService(@RequestParam(name = "city", required = true) String cityName) {
        if (!isCityNameValid(cityName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EMPTY_CITY_NAME_VALIDATION_MESSAGE);
        }
        Map<String, Object> requestCamelMessageHeaders = new CamelMessageHeadersMapBuilder()
                .withCityName(cityName)
                .build();
        String externalServiceResponse = template.requestBodyAndHeaders(
                template.getDefaultEndpoint(), null, requestCamelMessageHeaders, String.class);
        return ResponseEntity.ok(externalServiceResponse);
    }

    private boolean isCityNameValid(String cityName) {
        return StringUtils.isNotBlank(cityName);
    }

}
