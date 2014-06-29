package org.realrest.domain.repository.impl;

import org.realrest.domain.City;
import org.realrest.domain.Hotel;
import org.realrest.domain.repository.CityRepository;
import org.realrest.domain.repository.HotelRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class MockedDataLoader {

    @Inject
    private HotelRepository hotelRepository;

    @Inject
    private CityRepository cityRepository;

    public void load() {
        saveCitiesAndHotels();
    }

    private void saveCitiesAndHotels() {
        final City lviv = new City();
        lviv.setName("Львов");
        cityRepository.create(lviv);

        final City kyiv = new City();
        kyiv.setName("Киев");
        cityRepository.create(kyiv);

        final Hotel vintageBoutique = new Hotel();
        vintageBoutique.setName("Винтаж Бутик Отель");
        hotelRepository.create(vintageBoutique);

        final Hotel premierPalace = new Hotel();
        premierPalace.setName("Премьер Палас");
        hotelRepository.create(premierPalace);
    }

}
