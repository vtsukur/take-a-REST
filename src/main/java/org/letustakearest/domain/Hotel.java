package org.letustakearest.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Hotel extends IdentifiableAndVersioned {

    private City city;

    private String name;

    private Set<Place> places = new HashSet<>();

}
