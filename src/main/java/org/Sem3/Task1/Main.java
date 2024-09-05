package org.Sem3.Task1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        UserData user = new UserData("Katea", "451dddd", 14);

        try (FileOutputStream fileOutputStream = new FileOutputStream("userdata.bin")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            System.out.println("Written object");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileInputStream fileInputStream = new FileInputStream("userdata.bin")) {
            ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
            user = (UserData) objectInputStream.readObject();
            System.out.println("Read object");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("object deserialized");
        System.out.println("name: " + user.getName());
        System.out.println("age: " + user.getAge());
        System.out.println("null psw: " + user.getPassword());
    }
}
