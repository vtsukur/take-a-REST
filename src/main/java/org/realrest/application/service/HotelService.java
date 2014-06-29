package org.realrest.application.service;

import org.realrest.domain.Hotel;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface HotelService {

    Collection<Hotel> findAll();

}
