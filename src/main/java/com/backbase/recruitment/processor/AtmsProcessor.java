package com.backbase.recruitment.processor;

import com.backbase.recruitment.model.AtmLocation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

public class AtmsProcessor implements Processor {

    private static final String CORRUPTED_JSON_BEGINNING = ")]}',";

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getIn();
        String body = (String) message.getBody();
        body = removeCorruptedJsonBeginning(body);
        String atmLocations = getFormattedJson(body);
        message.setBody(atmLocations);
        exchange.setOut(message);
    }

    private String getFormattedJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AtmLocation> atmLocations = objectMapper.readValue(json, new TypeReference<List<AtmLocation>>() {});
        return objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(atmLocations);
    }

    private String removeCorruptedJsonBeginning(String body) {
        return body.replace(CORRUPTED_JSON_BEGINNING, StringUtils.EMPTY);
    }
}
