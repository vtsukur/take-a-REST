package org.letustakearest.domain;

/**
 * @author volodymyr.tsukur
 */
public abstract class Identifiable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

}
