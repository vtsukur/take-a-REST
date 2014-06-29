package org.realrest.domain.repository.impl;

import org.realrest.domain.Room;
import org.realrest.domain.repository.RoomRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryRoomRepository extends BaseInMemoryRepository<Room> implements RoomRepository {

    public InMemoryRoomRepository() {
        super("Room");
    }

}
