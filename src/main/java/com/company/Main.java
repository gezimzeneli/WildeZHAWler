package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static int simDuration;
    private static int intersectionsAmount;
    private static int streetsAmount;
    private static int carsAmount;
    private static int score;
    private static HashMap<String,Street> streets = new HashMap<>();
    private static HashMap<Integer, Intersection> intersections = new HashMap<>();
    private static List<Car> cars = new ArrayList<>();
    private static Intersection intersectionStart = new Intersection();
    private static Intersection intersectionEnd = new Intersection();

    public static void main(String[] args) {
        System.out.println("Was luegsh?!?!");
        System.err.println("Ich meins ernst!!!");
        Main main = new Main();
        String[] files = {"data/a", "data/b", "data/c", "data/d", "data/e", "data/f"};
        //String[] files = {"data/a", "data/b", "data/d"};

        for (String file : files) {
            main.read(file + ".txt");
        }
    }

    private void read(String file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] items;
            //Read first line
            if ((line = br.readLine()) != null) {
                items = line.split(" ");
                simDuration = Integer.parseInt(items[0]);
                intersectionsAmount = Integer.parseInt(items[1]);
                streetsAmount = Integer.parseInt(items[2]);
                carsAmount = Integer.parseInt(items[3]);
                score = Integer.parseInt(items[4]);
            }
            for(int i = 0; i < streetsAmount; i++) {
                if ((line = br.readLine()) != null) {
                    items = line.split(" ");

                    intersectionStart.setId(Integer.parseInt(items[0]));

                    intersectionEnd.setId(Integer.parseInt(items[1]));
                    Street street = new Street();
                    street.setName(items[2]);
                    street.setDuration(Integer.parseInt(items[3]));
                    street.setFrom(intersectionStart);
                    street.setTo(intersectionEnd);
                    intersectionStart.addOutput(street);
                    intersectionEnd.addInput(street);
                    intersections.put(intersectionStart.getId(), intersectionStart);
                    intersections.put(intersectionEnd.getId(), intersectionEnd);
                    streets.put(street.getName(),street);
                }
            }

                for ( int j = 0; j < carsAmount; j++){
                    if((line = br.readLine()) != null){
                        items = line.split( " ");
                        Car car = new Car();
                        car.setId(j);

                        for(int k = 0; k < Integer.parseInt(items[0])-1; k++){
                            car.addStreet(streets.get(items[k+1]));
                            streets.get(items[k+1]).addCar(car);
                        }
                        cars.add(car);

                    }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
