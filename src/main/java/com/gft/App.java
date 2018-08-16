package com.gft;


import com.gft.enums.DishType;
import com.gft.enums.TimeOfDay;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class App {

    public App() {
    }

    public static void main(String[] args) {
        new App().readDishesInput();
    }

    public String[] printDishes(String[] dishes) {

        if (containsTimeOfDay(dishes)) {

            String[] onlyDishes = getOnlyDishes(dishes);
            String[] dishesWithoutDup = Arrays.stream(onlyDishes).distinct().toArray(String[]::new);
            String[] namedDishes = new String[dishesWithoutDup.length];
            Arrays.sort(dishesWithoutDup);

            if (TimeOfDay.MORNING.asLowerCase().equals(getTimeOfDay(dishes))) {
                int coffeeOccurrences = getCoffeeOccurrences(dishes);
                printDishes(dishesWithoutDup, namedDishes, coffeeOccurrences, String.valueOf(DishType.COFFEE.getOrder()), TimeOfDay.MORNING.asLowerCase());
                System.out.println(String.join(",", namedDishes));
                return namedDishes;
            } else if (TimeOfDay.NIGHT.asLowerCase().equals(getTimeOfDay(dishes))) {
                int potatoesOcurrences = getPotatoOccurrences(dishes);
                printDishes(dishesWithoutDup, namedDishes, potatoesOcurrences, String.valueOf(DishType.POTATO.getOrder()), TimeOfDay.NIGHT.asLowerCase());
                System.out.println(String.join(",", namedDishes));
                return namedDishes;
            }

        } else {
            System.out.println("Time of day is required");
        }

        return null;

    }

    private void printDishes(String[] dishesWithoutDup, String[] namedDishes, int ocurrences, String dishTypeOrder, String timeOfDay) {

        int i = 0;
        for (String onlyDish : dishesWithoutDup) {
            if (onlyDish.trim().equals(dishTypeOrder)) {
                if (ocurrences > 1) {
                    namedDishes[i] = String.format("%s(%dx)", DishType.getDishByOrderAndTimeOfDay(Integer.parseInt(onlyDish.trim()), timeOfDay), ocurrences);
                } else {
                    namedDishes[i] = DishType.getDishByOrderAndTimeOfDay(Integer.parseInt(onlyDish.trim()), timeOfDay);
                }
            } else {
                namedDishes[i] = DishType.getDishByOrderAndTimeOfDay(Integer.parseInt(onlyDish.trim()), timeOfDay);
            }

            i++;
        }
    }

    String[] getOnlyDishes(String[] dishes) {
        return Arrays.stream(dishes).filter(s -> !s.equals("morning") && !s.equals("night")).map(String::trim).toArray(String[]::new);
    }

    Boolean containsTimeOfDay(String[] dishes) {
        return Arrays.stream(dishes).anyMatch(s -> s.equalsIgnoreCase(TimeOfDay.MORNING.asLowerCase()) || s.equalsIgnoreCase(TimeOfDay.NIGHT.asLowerCase()));
    }

    String getTimeOfDay(String[] dishes) {
        return Arrays.stream(dishes).filter(s -> s.equalsIgnoreCase(TimeOfDay.MORNING.asLowerCase()) || s.equalsIgnoreCase(TimeOfDay.NIGHT.asLowerCase())).map(String::trim).findAny().orElse(null);
    }

    int getCoffeeOccurrences(String[] dishes) {
        return Collections.frequency(Arrays.asList(dishes), String.valueOf(DishType.COFFEE.getOrder()));
    }

    int getPotatoOccurrences(String[] dishes) {
        return Collections.frequency(Arrays.asList(dishes), String.valueOf(DishType.POTATO.getOrder()));
    }

    private void readDishesInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            String line;
            while (!(line = scanner.nextLine()).isEmpty()) {
                String[] dishes = line.split(",");
                String[] dishesTrimmed = Arrays.stream(dishes).map(String::trim).toArray(String[]::new);
                String[] onlyDishes = getOnlyDishes(dishesTrimmed);

                boolean hasError = false;
                for (String dish : onlyDishes) {
                    boolean hasOptionSelected = Arrays.stream(DishType.values()).anyMatch(dishType -> dish.equals(String.valueOf(dishType.getOrder())));
                    if (line.startsWith("morning") && !hasOptionSelected) {
                        System.out.println(line);
                        System.out.println("Dish types for morning are:\n 1 - eggs\n 2 - toast\n 3 - coffee");
                        System.err.println(String.format("Wrong dish type #%s detected!", dish));
                        hasError = true;
                    } else if (line.startsWith("night") && !hasOptionSelected) {
                        System.out.println(line);
                        System.out.println("Dish types for night are:\n 1 - steak\n 2 - potato\n 3 - wine\n 4 - cake");
                        System.err.println(String.format("Wrong dish type #%s detected!", dish));
                        hasError = true;

                    }
                }

                if(!hasError) {
                    printDishes(dishesTrimmed);
                }

            }
        }
    }
}
