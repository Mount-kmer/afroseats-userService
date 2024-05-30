package com.example.afroseats_userService.config;

import com.example.afroseats_userService.utility.StringToEnumConverter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@ComponentScan("com.example.afroseats_userService")
//@EntityScan("com.example.afroseats_userService.entity")
public class ApplicationConfig implements WebMvcConfigurer {

    private final StringToEnumConverter stringToEnumConverter;

    public ApplicationConfig(StringToEnumConverter stringToEnumConverter) {
        this.stringToEnumConverter = stringToEnumConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToEnumConverter);
    }
}
