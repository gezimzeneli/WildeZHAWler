package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Writer {

    private BufferedWriter bufferedWriter;

    public Writer(String filename) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResult(List<Intersection> intersections){
        write(String.valueOf(intersections.size()));
        newLine();
        for (Intersection intersection : intersections){
            write(String.valueOf(intersection.getId()));
            newLine();
            Map<Street, Integer> schedule = intersection.getSchedule();
            write(String.valueOf(schedule.size()));
            newLine();


            for (Street street : schedule.keySet()){
                write(String.valueOf(street.getName()));
                write(" ");
                write(String.valueOf(street.getDuration()));
                newLine();
            }

        }
        close();
    }

    private void write(String string){
        try {
            bufferedWriter.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newLine(){
        write("\n");
    }

    private void close(){
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
