package com.city.practice_city.service;

import com.city.practice_city.model.Building;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Override
    public void create(Building building) {
        return;
    }

    @Override
    public List<Building> readAll() {
        return null;
    }

    @Override
    public Building read(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
