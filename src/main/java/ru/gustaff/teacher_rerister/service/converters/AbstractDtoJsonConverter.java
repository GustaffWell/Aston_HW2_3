package ru.gustaff.teacher_rerister.service.converters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class AbstractDtoJsonConverter<T> {

    private final Class<T> clazz;

    protected AbstractDtoJsonConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String toJson(T dto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

    public T fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }
}
