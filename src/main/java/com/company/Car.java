package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Car {
    long id;
    Queue<Street> route;


    public Car(){
        route = new LinkedList<>();
    }

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

    public void addStreet(Street street){
        route.add(street);
    }
}
