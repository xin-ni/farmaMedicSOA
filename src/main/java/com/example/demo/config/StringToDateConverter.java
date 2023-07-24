package com.example.demo.config;

import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    private final String dateFormat;

    public StringToDateConverter(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public Date convert(String source) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            return formatter.parse(source);
        } catch (ParseException e) {
            // Manejar la excepción si el formato no coincide
            return null;
        }
    }
}
