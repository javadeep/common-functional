package com.javadeep.functional.lang.data;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Consisting of length of a time unit.
 *
 * @author baojie
 * @since 1.0.0
 */
public final class Duration {

    /**
     * Duration for zero.
     */
    public static final Duration NONE = new Duration(0, TimeUnit.MILLISECONDS);

    private final long length;
    private final TimeUnit timeUnit;

    /**
     * Construct the instance of <code>Duration</code> by length and timeUnit.
     *
     * @param length length.
     * @param timeUnit timeUnit.
     * @throws NullPointerException if {@code timeUnit} is null.
     */
    public Duration(long length, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "timeUnit is null");
        this.length = length;
        this.timeUnit = timeUnit;
    }

    /**
     * Returns the Duration in nanoseconds.
     *
     * @return The converted duration.
     */
    public long toNanos() {
        return timeUnit.toNanos(length);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
                || (obj instanceof Duration && Objects.equals(toNanos(), ((Duration) obj).toNanos()));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(toNanos());
    }

    @Override
    public String toString() {
        return String.format("Duration(%d)", toNanos());
    }
}
