package org.Sem3.HM;

import java.io.*;

public class Person implements Externalizable {
    private Long id;
    private String name;
    private int age;
    transient String GPA;

    public Person() {
    }

    public Person(Long id, String name, int age, String GPA) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(id);
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readLong();
        name = (String) in.readObject();
        age = in.readInt();
    }

    @Override
    public String toString() {
        return "Person {" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", GPA='" + GPA + '\'' +
                '}';
    }
}
