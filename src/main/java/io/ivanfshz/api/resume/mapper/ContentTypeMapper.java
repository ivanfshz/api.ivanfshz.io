package io.ivanfshz.api.resume.mapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentTypeMapper {

    PDF(MediaType.APPLICATION_PDF, "pdf"),
    HTML(MediaType.TEXT_HTML, "html"),
    JSON(MediaType.APPLICATION_JSON, "json");

    private MediaType mediaType;
    private String format;

    public static MediaType toMediaType(final String format){
        return Arrays.stream(ContentTypeMapper.values())
                .filter(value -> value.format.equals(format.toLowerCase()))
                .findAny()
                .map(value -> value.mediaType)
                .orElseThrow(() -> new IllegalArgumentException("Couldn't get any media type that matches with: " + format));
    }
}