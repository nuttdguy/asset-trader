package com.assettrader.utils;

import java.time.LocalDate;
import java.sql.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements  AttributeConverter<LocalDate, Date> {
    
	@Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        return Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
        return databaseValue.toLocalDate();
    }

}