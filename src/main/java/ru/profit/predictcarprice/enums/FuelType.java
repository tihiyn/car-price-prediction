package ru.profit.predictcarprice.enums;

import lombok.Getter;

@Getter
public enum FuelType {
    GASOLINE("Бензин"),
    HYBRID("Гибрид"),
    GAS_AND_GAS_CYLINDER("Газ, газобаллонное оборудование"),
    ELECTRIC("Электро"),
    GASOLINE_AND_GAS_CYLINDER("Бензин, газобаллонное оборудование"),
    GAS("Газ");

    private final String name;

    FuelType(String name) {
        this.name = name;
    }
}
