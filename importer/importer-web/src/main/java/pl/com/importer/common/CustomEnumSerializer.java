package pl.com.importer.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CustomEnumSerializer extends JsonSerializer<CustomEnumSupport> {

    @Override
    public void serialize(CustomEnumSupport customEnumSupport, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", customEnumSupport.name());
        jsonGenerator.writeStringField("description", customEnumSupport.getDescription());
        jsonGenerator.writeEndObject();
    }
}
