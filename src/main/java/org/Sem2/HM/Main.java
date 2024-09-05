package org.Sem2.HM;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        analyzeClass(new String());
    }

    private static void analyzeClass(Object obj) {
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            String modifiers = Modifier.toString(method.getModifiers());
            String returnType = method.getReturnType().toString();
            String parameters = Arrays.toString(method.getParameterTypes());
            System.out.printf("%s %s, return type: %s, parameters: %s\n"
                    , modifiers, method.getName(), returnType, parameters);
        }
    }
}
