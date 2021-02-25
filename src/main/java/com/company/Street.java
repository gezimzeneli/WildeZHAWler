package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class Street {
    Intersection from;
    Intersection to;
    int duration;
    String name;

    Queue<Car> queue;

    public Street(){
        queue = new LinkedList<>();
    }


    public Intersection getFrom() {
        return from;
    }

    public void setFrom(Intersection from) {
        this.from = from;
    }

    public Intersection getTo() {
        return to;
    }

    public void setTo(Intersection to) {
        this.to = to;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue<Car> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Car> queue) {
        this.queue = queue;
    }

    public void addCar(Car car){
        queue.add(car);
    }
}
