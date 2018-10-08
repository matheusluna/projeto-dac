package io.github.dac.rhecruta.models.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Converter(autoApply = false)
public class TimeConverter implements AttributeConverter<LocalDateTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalDateTime localDateTime) {
        return (localDateTime == null ?  null : Time.valueOf(localDateTime.toLocalTime()));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Time time) {
        return (time == null ? null : LocalDateTime.of(null, time.toLocalTime()));
    }
}
