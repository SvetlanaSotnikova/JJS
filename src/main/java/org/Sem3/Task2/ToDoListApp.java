package org.Sem3.Task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    public static final String FILE_JSON = "tasks.json";
    public static final String FILE_BIN = "tasks.bin";
    public static final String FILE_XML = "tasks.xml";

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void addNewTask(Scanner scanner, List<ToDo> tasks) {
        System.out.println("Enter the name of the task: ");
        String newTaskTitle = scanner.nextLine();
        tasks.add(new ToDo(newTaskTitle));
        saveTaskToFile(FILE_JSON, tasks);
        saveTaskToFile(FILE_BIN, tasks);
        saveTaskToFile(FILE_XML, tasks);
        System.out.println("the new task added");
    }

    public static void saveTaskToFile(String fileName, List<ToDo> tasks) {
        try {
            if (fileName.endsWith(".json")) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                mapper.writeValue(new File(fileName), tasks);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(tasks);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), tasks);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ToDo> loadTasksFromFile(String fileName) {
        List<ToDo> tasks = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    tasks = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, ToDo.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        tasks = (List<ToDo>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    tasks = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, ToDo.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return tasks;
    }

    public static void markTaskAsDone(Scanner scanner, List<ToDo> tasks) {
        System.out.println("Enter the index of the task you would like to mark done: ");
        String input = scanner.nextLine();
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setDone(true);
                saveTaskToFile(FILE_JSON, tasks);
                saveTaskToFile(FILE_BIN, tasks);
                saveTaskToFile(FILE_XML, tasks);
                System.out.printf("The task %d has been marked as done", index);
            } else {
                System.out.println("incorrect the index of task. Try again :)");
            }
        } catch (NumberFormatException e) {
            System.out.println("incorrect the index of task. Try again :)");
        }
    }

    public static void displayTasks(List<ToDo> tasks) {
        System.out.println("Task list");
        for (int i = 0; i < tasks.size(); i++) {
            ToDo task = tasks.get(i);
            String status = task.isDone() ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + status + ": " + task.getTitle());
        }
    }

}
