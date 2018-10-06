package io.github.dac.rhecruta.models.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Converter(autoApply = false)
public class DateConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDate) {
        return (localDate == null ? null : Date.valueOf(localDate.toLocalDate()));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : LocalDateTime.of(sqlDate.toLocalDate(), null));
    }
}
