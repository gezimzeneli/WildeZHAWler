package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static int simDuration;
    private static int intersectionsAmount;
    private static int streetsAmount;
    private static int carsAmount;
    private static int score;

    public static void main(String[] args) {
        System.out.println("Was luegsh?!?!");
        System.err.println("Ich meins ernst!!!");
        Main main = new Main();
        String[] files = {"data/a", "data/b", "data/c", "data/d", "data/e", "data/f"};
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
