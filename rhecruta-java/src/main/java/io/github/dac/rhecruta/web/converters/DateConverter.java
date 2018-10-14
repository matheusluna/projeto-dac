package io.github.dac.rhecruta.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FacesConverter(value = "converter.Date", forClass = LocalDate.class)
public class DateConverter implements Converter {

    private DateTimeFormatter formmater = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value == null) return null;

        return LocalDate.parse(value, formmater);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        if (value == null) return "";

        return formmater.format((LocalDate) value);
    }
}