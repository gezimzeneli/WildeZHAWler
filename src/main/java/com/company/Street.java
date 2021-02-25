package com.company;

import java.util.Queue;

public class Street {
    Intersection from;
    Intersection to;
    int duration;
    String name;

    Queue<Car> queue;

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}