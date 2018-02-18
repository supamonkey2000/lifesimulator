package com.milada.gameoflife;

public class Life {
    private String name;
    private Car car;
    private Job job;
    private House house;
    private Country country;

    Life(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
