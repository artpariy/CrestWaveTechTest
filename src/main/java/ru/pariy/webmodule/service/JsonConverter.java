package ru.pariy.webmodule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.webmodule.api.jaxb.EnvelopeType;

public class JsonConverter {
    private static final Logger logger = LogManager.getLogger(JsonConverter.class);

    public String convertToJson(EnvelopeType envelopeType) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString;
        try {
            jsonInString = mapper.writeValueAsString(envelopeType);
        } catch (Exception exception) {
            throw new JsonConverterException("Convert Json error", exception);
        }
        logger.info("Converted JSON: " + jsonInString);
        return jsonInString;
    }
}
