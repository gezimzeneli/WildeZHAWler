package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Solver {

    public static void solve(List<Car> cars, List<Intersection> intersections){

        for (Car car : cars){
            for (int i = 0; i < car.route.size(); i++) {
                Street street = car.route.poll();
                street.counter=street.counter+1;
            }
        }

        for (Intersection intersection : intersections) {
            List<Street> streets = intersection.input.stream().sorted(Comparator.comparingInt(Street::getCounter)).collect(Collectors.toList());

            int counter = 1;
            for (Street street : streets) {
                intersection.schedule.put(street, counter);
                counter++;
            }
        }

        
    }


}
