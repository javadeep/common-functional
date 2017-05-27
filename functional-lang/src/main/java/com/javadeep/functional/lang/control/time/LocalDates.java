package com.javadeep.functional.lang.control.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * LocalDate Tools
 *
 * @author baojie
 * @since 1.0.0
 */
public final class LocalDates {

    private LocalDates() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    /**
     * Get the start LocalDateTime of LocalDate
     *
     * @param localDate The localDate
     * @return The start localDateTime of localDate
     * @throws NullPointerException if {@code localDate} is null
     */
    public static LocalDateTime atStartOfDay(LocalDate localDate) {

        Objects.requireNonNull(localDate, "localDate is null");
        return localDate.atStartOfDay();
    }

    /**
     * Get the end LocalDateTime of LocalDate
     *
     * @param localDate The localDate
     * @return The end localDateTime of localDate
     * @throws NullPointerException if {@code localDate} is null
     */
    public static LocalDateTime atEndOfDay(LocalDate localDate) {

        Objects.requireNonNull(localDate, "localDate is null");
        return localDate.atTime(LocalTime.MAX);
    }

    /**
     * Get the start epoch second of localDate
     *
     * @param localDate The localDate
     * @return The start epoch second of localDate
     * @throws NullPointerException if {@code localDate} is null
     */
    public static int toStartEpochSecond(LocalDate localDate) {

        Objects.requireNonNull(localDate, "localDate");
        return (int) atStartOfDay(localDate).toEpochSecond(ZoneOffsets.getLocalZoneOffset());
    }

    /**
     * Get the end epoch second of localDate
     *
     * @param localDate The localDate
     * @return The end epoch second of localDate
     * @throws NullPointerException if {@code localDate} is null
     */
    public static int toEndEpochSecond(LocalDate localDate) {

        Objects.requireNonNull(localDate, "localDate is null");
        return (int) atEndOfDay(localDate).toEpochSecond(ZoneOffsets.getLocalZoneOffset());
    }
}

