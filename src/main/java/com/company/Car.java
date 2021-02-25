package com.company;

import java.util.Queue;

public class Car {
    long id;
    Queue<Street> route;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Queue<Street> getRoute() {
        return route;
    }

    public void setRoute(Queue<Street> route) {
        this.route = route;
    }
}
