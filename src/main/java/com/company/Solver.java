package com.company;

import java.util.List;
import java.util.Map;

public class Solver {

    public void solve(List<Car> cars){


        Map<Intersection, Integer> brudi;



        for (Car car : cars){
            for (int i = 0; i < car.route.size(); i++) {
                Intersection intersection = car.route.poll().to;
            }
        }



    }


}
