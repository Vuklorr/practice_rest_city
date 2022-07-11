package com.city.practice_city.service;

import com.city.practice_city.model.Building;

import java.util.List;
import java.util.UUID;

public interface BuildingService {
    void create(Building building);

    List<Building> readAll();

    Building read(UUID id);

    boolean delete(UUID id);

}
