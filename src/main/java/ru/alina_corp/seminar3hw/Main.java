package ru.alina_corp.seminar3hw;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Alice", 20, 3.5);


        System.out.println("Имя: " + student.getName());
        System.out.println("Возраст: " + student.getAge());
        System.out.println("GPA: " + student.getGPA());
        System.out.println();

        // Сериализация объекта в JSON файл

        try (FileOutputStream fileOut = new FileOutputStream("student.json");
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(student);
            System.out.println("Объект Student сериализован.");
        }

        // Десериализация объекта из JSON файла
        try (FileInputStream fileIn = new FileInputStream("student.json");
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            student = (Student) in.readObject();
            System.out.println("Объект Student десериализован.");
        }

        System.out.println("Имя: " + student.getName());
        System.out.println("Возраст: " + student.getAge());
        System.out.println("GPA (должен быть null, так как transient): " + student.getGPA());


        // Сериализация объекта в XML файл

        try (FileOutputStream fileOut = new FileOutputStream("studentdata.xml");
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(student);
            System.out.println("Объект Student сериализован.");
        }

        // Десериализация объекта из XML файла
        try (FileInputStream fileIn = new FileInputStream("studentdata.xml");
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            student = (Student) in.readObject();
            System.out.println("Объект Student десериализован.");
        }

        System.out.println("Имя: " + student.getName());
        System.out.println("Возраст: " + student.getAge());
        System.out.println("GPA (должен быть null, так как transient): " + student.getGPA());

    }
}