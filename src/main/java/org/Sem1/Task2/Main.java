package org.Sem1.Task2;

public class Main {
    public static void main(String[] args) {
        UMarket market = new UMarket();
        System.out.println("Welcome to the market");
        market.printThings(Thing.class);
        System.out.println(market.getThings(Food.class));
        System.out.println(market.getThingsByIndex(Thing.class, 5));
    }
}