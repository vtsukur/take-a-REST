package org.letustakearest.domain.repository.impl;

import org.letustakearest.domain.City;
import org.letustakearest.domain.Hotel;
import org.letustakearest.domain.Place;
import org.letustakearest.domain.repository.CityRepository;
import org.letustakearest.domain.repository.HotelRepository;
import org.letustakearest.domain.repository.PlaceRepository;

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

    @Inject
    private PlaceRepository placeRepository;

    public void load() {
        saveCitiesAndHotels();
    }

    private void saveCitiesAndHotels() {
        final City lviv = new City();
        lviv.setName("Lviv");
        cityRepository.create(lviv);

        final City kyiv = new City();
        kyiv.setName("Kyiv");
        cityRepository.create(kyiv);

        final Hotel vintageBoutique = new Hotel();
        vintageBoutique.setName("Vintage Boutique Hotel");
        vintageBoutique.setCity(lviv);
        vintageBoutique.getPlaces().add(placeRepository.create(new Place(vintageBoutique, Place.Type.APARTMENT, Place.Category.SINGLE, 500)));
        vintageBoutique.getPlaces().add(placeRepository.create(new Place(vintageBoutique, Place.Category.DOUBLE, 800)));
        hotelRepository.create(vintageBoutique);

        final Hotel nobilis = new Hotel();
        nobilis.setName("Nobilis");
        nobilis.setCity(lviv);
        nobilis.getPlaces().add(placeRepository.create(new Place(nobilis, Place.Category.JUNIOR_SUITE, 2500)));
        nobilis.getPlaces().add(placeRepository.create(new Place(nobilis, Place.Category.SUITE, 4500)));
        hotelRepository.create(nobilis);

        final Hotel premierPalace = new Hotel();
        premierPalace.setName("Premier Palace");
        premierPalace.setCity(kyiv);
        premierPalace.getPlaces().add(placeRepository.create(new Place(premierPalace, Place.Category.SINGLE, 1000)));
        premierPalace.getPlaces().add(placeRepository.create(new Place(premierPalace, Place.Category.DOUBLE, 1500)));
        hotelRepository.create(premierPalace);

        final Hotel presidentHotelKyiv = new Hotel();
        presidentHotelKyiv.setName("President");
        presidentHotelKyiv.setCity(kyiv);
        presidentHotelKyiv.getPlaces().add(placeRepository.create(new Place(presidentHotelKyiv, Place.Category.DOUBLE, 1400)));
        presidentHotelKyiv.getPlaces().add(placeRepository.create(new Place(presidentHotelKyiv, Place.Category.QUEEN, 2200)));
        hotelRepository.create(presidentHotelKyiv);
    }

}
