package dev.michael.core.enums;

public enum ApplicableCharactersEnum {
    Digits("Digits",48, 57),

    LowerCase("LowerCase",97, 122);

    private String name;

    private int min;

    private int max;


    ApplicableCharactersEnum(String name, int min, int max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }
    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
