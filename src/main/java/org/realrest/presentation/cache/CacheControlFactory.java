package org.realrest.presentation.cache;

import javax.ws.rs.core.CacheControl;

/**
 * @author volodymyr.tsukur
 */
public final class CacheControlFactory {

    private CacheControlFactory() {

    }

    public static CacheControl oneDay() {
        return CacheControl.valueOf("max-age=" + 30);
    }

}
