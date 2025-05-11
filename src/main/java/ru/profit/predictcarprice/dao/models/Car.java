package ru.profit.predictcarprice.dao.models;

import lombok.Getter;
import lombok.Setter;
import ru.profit.predictcarprice.enums.*;

@Setter
@Getter
public class Car {
    private Integer yearOfProduction;
    private Gearbox gearbox;
    private WheelDrive wheelDrive;
    private Integer mileage;
    private String bodyType;
    private Condition condition;
    private Owners owners;
    private Double engineVolume;
    private Integer enginePower;
    private FuelType fuelType;
}
