package ru.profit.predictcarprice.enums;

import lombok.Getter;

@Getter
public enum WheelDrive {
    FULL("полный"),
    FRONT("передний"),
    REAR("задний");

    private final String name;

    WheelDrive(String name) {
        this.name = name;
    }
}
