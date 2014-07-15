package org.letustakearest.infrastructure.jaxrs;

import lombok.Getter;
import lombok.Setter;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Error {

    private String type;

    private String message;

    private String action;

    private String link;

}
