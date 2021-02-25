package com.company;

public class Simulation {
    int duration;       // D
    int intersections;  // I
    int streets;        // S
    int cars;           // V
    int bonusPoints;    // F


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIntersections() {
        return intersections;
    }

    public void setIntersections(int intersections) {
        this.intersections = intersections;
    }

    public int getStreets() {
        return streets;
    }

    public void setStreets(int streets) {
        this.streets = streets;
    }

    public int getCars() {
        return cars;
    }

    public void setCars(int cars) {
        this.cars = cars;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
