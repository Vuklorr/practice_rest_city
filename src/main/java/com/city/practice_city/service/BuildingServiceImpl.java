package com.city.practice_city.service;

import com.city.practice_city.model.Building;
import com.city.practice_city.util.ParseUUID;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final String file = "file.csv";

    @Override
    public void create(Building building) {
        List<Building> buildings = new ArrayList<>(readFromCsv());
        buildings.add(building);
        writeToCsv(buildings);
    }

    @Override
    public List<Building> readAll() {
        return readFromCsv();
    }

    @Override
    public Building read(UUID id) {
        return readFromCsv().stream()
                .filter(building -> building.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        List<Building> buildings = readFromCsv();
        Optional<Building> buildingOptional = buildings.stream()
                .filter(building -> building.getId().equals(id)).findFirst();
        if(buildingOptional.isEmpty())
            return false;
        buildings.remove(buildingOptional.get());
        writeToCsv(buildings);
        return true;
    }

    @Override
    public String count() {
        return "Количество объектов класса в файле = " + numberFromCsV();
    }

    private int numberFromCsV() {
        try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getReadProcessors();
            int i = 0;
            while (beanReader.read(Building.class, header, processors) != null) {
                i++;
            }
            return i;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Building> readFromCsv() {
        try(ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getReadProcessors();

            List<Building> buildings = new ArrayList<>();
            Building building;
            while((building = beanReader.read(Building.class, header, processors)) != null) {
                buildings.add(building);
            }
            return buildings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToCsv(List<Building> buildings) {
        try(ICsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter(file), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] header = new String[] { "id", "type", "street", "number", "date", "floor", "owner"};
            final CellProcessor[] processors = getWriteProcessors();

            beanWriter.writeHeader(header);
            for(final Building building : buildings) {
                beanWriter.write(building, header, processors);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CellProcessor[] getReadProcessors() {
        return new CellProcessor[] {
                new ParseUUID(), // id
                new NotNull(), // type
                new NotNull(), // street
                new ParseInt(), // number
                new ParseDate("yyyy-MM-dd"), // date
                new ParseInt(), // floor
                new NotNull(), // owner
        };
    }

    private static CellProcessor[] getWriteProcessors() {
        return new CellProcessor[] {
                new ParseUUID(), // id
                new NotNull(), // type
                new NotNull(), // street
                new NotNull(), // number
                new FmtDate("yyyy-MM-dd"), // date
                new NotNull(), // floor
                new NotNull(), // owner
        };
    }

}
