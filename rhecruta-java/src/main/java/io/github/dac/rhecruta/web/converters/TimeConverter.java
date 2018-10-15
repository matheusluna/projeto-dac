package io.github.dac.rhecruta.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@FacesConverter(value = "converter.Time", forClass = LocalTime.class)
public class TimeConverter implements Converter {

    private DateTimeFormatter formmater = DateTimeFormatter.ofPattern("HH:mm");

    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value == null) return null;

        return LocalTime.parse(value, formmater);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        if (value == null) return "";

        return formmater.format((LocalTime) value);
    }
}