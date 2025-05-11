package ru.profit.predictcarprice.enums;

import lombok.Getter;

@Getter
public enum Condition {
    OK("Не требует ремонта");

    private final String name;

    Condition(String name) {
        this.name = name;
    }
}
