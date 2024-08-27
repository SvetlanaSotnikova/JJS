package org.Sem1.Task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UMarket {

    private final Collection<Thing> things;

    public UMarket() {
        things = new ArrayList<>();
        initThings();
    }

    public void initThings() {
        things.add(new Pen());
        things.add(new Notebook());

        things.add(new Chicken());
        things.add(new Fruit());
        things.add(new OliveOil());

        things.add(new BalkCheese());
        things.add(new Chips());
        things.add(new ChocolateBar());

        things.add(new DumplingsBerries());
        things.add(new DumplingsMeat());
        things.add(new Cheburek());
    }

    public <T extends Thing> T getThingsByIndex(Class<T> tClass, int index) {
//        int counter = 1;
//        for (var thing : things) {
//            if (tClass.isAssignableFrom(thing.getClass())) {
//                if (index == counter++) {
//                    return (T) thing;
//                }
//            }
//        }
//        return null;
        AtomicInteger counter = new AtomicInteger(1);
        return things.stream()
                .filter(tClass::isInstance)
                .filter(thing -> index == counter.getAndIncrement())
                .map(tClass::cast)
                .findFirst().orElse(null);
    }


    public <T extends Thing> Collection<T> getThings(Class<T> tClass) {
//        Collection<T> list = new ArrayList<>();
//        for (var thing : things) {
//            if (tClass.isAssignableFrom(this.getClass())) {
//                list.add((T)thing);
//            }
//        }
//        return list;
        return things.stream()
                .filter(tClass::isInstance)
                .map(tClass::cast)
                .collect(Collectors.toList());
    }

    public <T extends Thing> void printThings(Class<T> tClass) {
        final int[] index = {1};
        things.stream()
                .filter(tClass::isInstance)
                .forEach(thing -> {
                    if (Food.class.isAssignableFrom(thing.getClass())) {
                        Food food = (Food) thing;
                        System.out.printf("[%d] %s (Proteins: %s, Fats: %s, Carbohydrates: %s)\n",
                                index[0]++, thing.getName(),
                                food.getProteins() ? "Yes" : "No",
                                food.getFats() ? "Yes" : "No",
                                food.getCarbohydrates() ? "Yes" : "No");
                    } else {
                        System.out.printf("[%d] %s\n", index[0]++, thing.getName());
                    }
                });
    }
}
