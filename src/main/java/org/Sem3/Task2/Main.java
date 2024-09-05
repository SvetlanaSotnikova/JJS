package org.Sem3.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.Sem3.Task2.ToDoListApp.*;

public class Main {
    public static void main(String[] args) {
        List<ToDo> tasks;
        File file = new File(FILE_JSON);
        if (file.exists() && !file.isDirectory()) {
            tasks = loadTasksFromFile(FILE_JSON);
        } else {
            tasks = prepareTasks();
        }

        displayTasks(tasks);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter an option:");
            System.out.println("""
                    1. Add a new task
                    2. Mark a task as done
                    3. Exit""");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addNewTask(scanner, tasks);
                case "2" -> markTaskAsDone(scanner, tasks);
                case "3" -> {
                    ToDoListApp.saveTaskToFile(FILE_JSON, tasks);
                    ToDoListApp.saveTaskToFile(FILE_BIN, tasks);
                    ToDoListApp.saveTaskToFile(FILE_XML, tasks);
                    System.out.println("Task added successfully.");
                    System.out.println("bye :))");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
            displayTasks(tasks);
        }

    }

    static List<ToDo> prepareTasks() {
        ArrayList<ToDo> tasks = new ArrayList<>();
        tasks.add(new ToDo("pet my cat"));
        tasks.add(new ToDo("feed my cat"));
        tasks.add(new ToDo("play with my cat"));
        return tasks;
    }
}
