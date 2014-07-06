package org.letustakearest.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public abstract class IdentifiableAndVersioned {

    private Long id;

    private int version;

}
