package com.city.practice_city.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Building {

    private UUID id = UUID.randomUUID();
    private String type;
    private String street;
    private int number;
    private Date date;
    private int floor;
    private String owner;

}
