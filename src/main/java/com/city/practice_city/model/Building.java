package com.city.practice_city.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Building {

    private UUID id = UUID.randomUUID();
    @NotBlank
    private String type;
    @NotBlank
    private String street;
    @Min(1)
    private Integer number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotNull
    private Integer floor;
    @NotBlank
    private String owner;

    public Building(String type, String street, Integer number, Date date, Integer floor, String owner) {
        this.type = type;
        this.street = street;
        this.number = number;
        this.date = date;
        this.floor = floor;
        this.owner = owner;
    }
}
