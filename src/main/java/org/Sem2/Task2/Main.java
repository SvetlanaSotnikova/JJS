package org.Sem2.Task2;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Car car = new Car(1988, "BMW", "Black");
        task2(car);
    }

    private static <T> void task2 (T obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.printf("%s: %s\n", field.getName(), field.get(clazz));
        }
    }
}
