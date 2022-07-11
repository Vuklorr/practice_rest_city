package com.city.practice_city.controller;

import com.city.practice_city.model.Building;
import com.city.practice_city.service.BuildingService;
import com.city.practice_city.util.ValidUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
public class BuildingController {
    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping(value = "/buildings")
    public ResponseEntity<?> create(@Valid @RequestBody Building building) {
        buildingService.create(building);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/buildings")
    public ResponseEntity<List<Building>> read() {
        final List<Building> building = buildingService.readAll();

        return building != null && !building.isEmpty()
                ? new ResponseEntity<>(building, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/buildings/{id}")
    public ResponseEntity<Building> read(@ValidUUID @PathVariable(name = "id") String id) {
        final Building building = buildingService.read(UUID.fromString(id));

        return building != null
                ? new ResponseEntity<>(building, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/buildings/{id}")
    public ResponseEntity<?> delete(@ValidUUID @PathVariable(name = "id") String id) {
        final boolean deleted = buildingService.delete(UUID.fromString(id));

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
