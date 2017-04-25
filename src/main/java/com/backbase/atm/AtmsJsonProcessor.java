package com.backbase.atm;

import com.backbase.atm.model.AtmLocation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

public class AtmsJsonProcessor implements Processor {

    private static final String CORRUPTED_JSON_BEGINNING = ")]}',";

    @Override
    public void process(Exchange exchange) throws Exception {
        setExchangeOutputAsFormattedJson(exchange);
    }

    private void setExchangeOutputAsFormattedJson(Exchange exchange) throws IOException {
        Message message = exchange.getIn();
        exchange.setOut(setNewMessageBodyAsFormattedJson(message));
    }

    private Message setNewMessageBodyAsFormattedJson(Message message) throws IOException {
        String body = (String) message.getBody();
        message.setBody(createNewMessageBodyAsFormattedJson(body));
        return message;
    }

    private String createNewMessageBodyAsFormattedJson(String messageBody) throws IOException {
        String atmLocations = formatJson(messageBody);
        return atmLocations;
    }

    private String formatJson(String messageBody) throws IOException {
        String json = removeCorruptedJsonBeginning(messageBody);
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
