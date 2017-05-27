package com.javadeep.functional.lang.control.time;

import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Locale;

/**
 * ZoneOffset Tools
 *
 * @author baojie
 * @since 1.0.0
 */
public final class ZoneOffsets {

    private ZoneOffsets() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    private static final ZoneOffset localZoneOffset = ZoneOffset.ofTotalSeconds(
            Calendar.getInstance(Locale.getDefault()).get(Calendar.ZONE_OFFSET) / 1000);

    /**
     * Get Local ZoneOffset
     *
     * @return The local ZoneOffset
     */
    public static ZoneOffset getLocalZoneOffset() {
        return localZoneOffset;
    }

}
