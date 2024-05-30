package com.example.afroseats_userService.utility;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class StringToEnumConverter implements Converter<String,Gender> {

    @Override
    public Gender convert(String value) {
        try {
            return Gender.valueOf(value.toUpperCase(Locale.ROOT));
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value '" + value + "supported");
        }
        }
}
