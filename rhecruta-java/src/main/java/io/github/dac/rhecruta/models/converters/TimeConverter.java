package io.github.dac.rhecruta.models.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Converter(autoApply = false)
public class TimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
        return (localTime == null ?  null : Time.valueOf(localTime));
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        return (time == null ? null : time.toLocalTime());
    }

}
