package com.backbase.atm;

import com.backbase.atm.model.RouteHeader;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AtmsController {

    @Produce(uri = "direct:atms")
    private ProducerTemplate template;


    @RequestMapping(path = "/atms", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getAtmsFromExternalService(@RequestParam(name = "city", required = true) String cityName) {
        Map<String, Object> requestCamelMessageHeaders = createMessageCityNameHeader(cityName);
        String externalServiceResponse = template.requestBodyAndHeaders(
                template.getDefaultEndpoint(), null, requestCamelMessageHeaders, String.class);
        return ResponseEntity.ok(externalServiceResponse);
    }

    private Map<String, Object> createMessageCityNameHeader(String cityName) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(RouteHeader.CITY_NAME.getName(), cityName);
        return headers;
    }
}
