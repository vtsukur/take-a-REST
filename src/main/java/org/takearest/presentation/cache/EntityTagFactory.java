package org.takearest.presentation.cache;

import javax.ws.rs.core.EntityTag;

/**
 * @author volodymyr.tsukur
 */
public final class EntityTagFactory {

    private EntityTagFactory() {

    }

    public static EntityTag entityTag(int value) {
        return new EntityTag(String.valueOf(value));
    }

}
