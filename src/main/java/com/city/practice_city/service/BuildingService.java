package com.city.practice_city.service;

import com.city.practice_city.model.Building;

import java.util.List;

public interface BuildingService {
    void create(Building building);

    List<Building> readAll();

    Building read(String id);

    boolean delete(String id);
}
