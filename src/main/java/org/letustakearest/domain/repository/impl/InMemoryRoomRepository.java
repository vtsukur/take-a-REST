package org.letustakearest.domain.repository.impl;

import org.letustakearest.domain.Room;
import org.letustakearest.domain.repository.RoomRepository;

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
