package ru.profit.predictcarprice.enums;

import lombok.Getter;

@Getter
public enum Gearbox {
    AUTOMATIC("автоматическая"),
    MECHANICAL("механическая"),
    ROBOTIC("роботизированная"),
    VARIATOR("вариатор");

    private final String name;

    Gearbox(String name) {
        this.name = name;
    }
}
