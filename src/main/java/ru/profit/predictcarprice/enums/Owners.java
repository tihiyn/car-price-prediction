package ru.profit.predictcarprice.enums;

import lombok.Getter;

@Getter
public enum Owners {
    ONE("1 владелец"),
    TWO("2 владельца"),
    THREE_OR_MORE("3 или более");

    private final String name;

    Owners(String name) {
        this.name = name;
    }
}
