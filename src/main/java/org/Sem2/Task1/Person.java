package org.Sem2.Task1;

public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = "Kate";
        this.age = 14;
    }

    public void displayInfo() {
        System.out.printf("Name: %s\nAge: %d\n", name, age);
    }
}
