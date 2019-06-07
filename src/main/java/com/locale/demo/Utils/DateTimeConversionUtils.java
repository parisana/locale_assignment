package com.locale.demo.Utils;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author Parisana
 */
public final class DateTimeConversionUtils {

    public static Instant convertToInstant(String toDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
        final TemporalAccessor temporalAccessor = dateTimeFormatter.parse(toDate);
        return Instant.from(temporalAccessor);
    }

}
