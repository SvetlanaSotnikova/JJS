package org.Sem1.Task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Cast<T extends Food> {

    private final ArrayList<T> foodstuff;
    private final UMarket market;
    private final Class<T> clazz;

    public Cast(Class<T> clazz, ArrayList<T> foodstuff, UMarket market) {
        this.clazz = clazz;
        this.foodstuff = foodstuff;
        this.market = market;
    }

    public Collection<T> getFoodstuff() {
        return foodstuff;
    }

    public void printThings() {
        AtomicInteger index = new AtomicInteger(1);
        foodstuff.forEach(food -> {
            System.out.printf("[%d] %s (Proteins: %s, Fats: %s, Carbohydrates: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Yes" : "No",
                    food.getFats() ? "Yes" : "No",
                    food.getCarbohydrates() ? "Yes" : "No");
        });
    }


}
