package pl.com.importer.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CustomEnumSerializer.class)
public interface CustomEnumSupport {
    String getDescription();

    String name();
}
