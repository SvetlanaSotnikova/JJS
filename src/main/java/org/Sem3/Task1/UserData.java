package org.Sem3.Task1;

import java.io.Serializable;

public class UserData implements Serializable {
    private final String name;
    private final int age;
    transient String password;

    public UserData(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
