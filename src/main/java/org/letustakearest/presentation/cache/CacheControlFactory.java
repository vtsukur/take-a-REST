package org.letustakearest.presentation.cache;

import javax.ws.rs.core.CacheControl;
import java.time.Duration;

/**
 * @author volodymyr.tsukur
 */
public final class CacheControlFactory {

    private CacheControlFactory() {

    }

    public static CacheControl oneDay() {
        return CacheControl.valueOf("max-age=" + Duration.ofDays(1).getSeconds());
    }

    public static CacheControl oneMinute() {
        return CacheControl.valueOf("max-age=" + Duration.ofMinutes(1).getSeconds());
    }

}
