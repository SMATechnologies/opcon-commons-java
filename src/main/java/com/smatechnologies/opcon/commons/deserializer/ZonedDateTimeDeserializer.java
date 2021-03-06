package com.smatechnologies.opcon.commons.deserializer;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;


/**
 * @author Pierre PINON
 */
public class ZonedDateTimeDeserializer implements IDeserializer<ZonedDateTime> {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String ZONE_PATTERN = "XXX";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern(DATE_TIME_PATTERN)
            .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 7, true)
            .appendPattern(ZONE_PATTERN)
            .toFormatter();

    public ZonedDateTime deserialize(String value) throws DeserializeException {
        if (value == null) {
            return null;
        }

        try {
            return ZonedDateTime.parse(value, DATE_TIME_FORMATTER);
        } catch (DateTimeException | IllegalArgumentException e) {
            throw new DeserializeException(e);
        }
    }
}
