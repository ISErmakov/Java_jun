package ru.alina_corp.seminar3hw;

import java.io.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class Student implements Serializable{
    private String name;
    private int age;
    transient double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return GPA;
    }
}

