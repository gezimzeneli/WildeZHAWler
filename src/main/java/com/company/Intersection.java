package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intersection {
    int id;
    List<Street> input = new ArrayList<>();
    List<Street> output = new ArrayList<>();
    Map<Street, Integer> schedule = new HashMap<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Street> getInput() {
        return input;
    }

    public void setInput(List<Street> input) {
        this.input = input;
    }

    public List<Street> getOutput() {
        return output;
    }

    public void setOutput(List<Street> output) {
        this.output = output;
    }

    public Map<Street, Integer> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<Street, Integer> schedule) {
        this.schedule = schedule;
    }
}
