package com.pokemongo.utilities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class TimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return (localDateTime == null ? Timestamp.valueOf(LocalDateTime.now()) : Timestamp.valueOf(localDateTime));
    }
    
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return (timestamp == null ? LocalDateTime.now() : timestamp.toLocalDateTime());
    }
}