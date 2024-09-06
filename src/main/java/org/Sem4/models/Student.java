package org.Sem4.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Entity
@Table(name = "students")
public class Student {
    private static final String[] names = new String[]{"Anatolii", "Misha", "Matfei", "Vladislav", "Putin", "Katea", "Sveta"};
    private static final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Student(int id,String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static Student create() {
        return new Student(names[random.nextInt(names.length)], random.nextInt(20, 26));
    }

    public void updateAge() {
        age = random.nextInt(20, 26);
    }

    public void updateName() {
        name = names[random.nextInt(names.length)];
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", age = " + age  +
                '}';
    }
}
