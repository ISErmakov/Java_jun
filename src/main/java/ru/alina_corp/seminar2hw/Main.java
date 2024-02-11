package ru.alina_corp.seminar2hw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> dog = Class.forName("ru.alina_corp.seminar2hw.Dog");
        Class<?> animal = Class.forName("ru.alina_corp.seminar2hw.Animal");
        Class<?> cat = Class.forName("ru.alina_corp.seminar2hw.Cat");
        Constructor<?>[] constructorsDog = dog.getConstructors();
        Constructor<?>[] constructorsCat = cat.getConstructors();
        Object[] animals = new Object[]{constructorsDog[0].newInstance("Buddy", 3, "Golden Retriever"),
                constructorsCat[0].newInstance("Whiskers", 5, true)};
        Method makeSound = animal.getDeclaredMethod("makeSound");
        Method displayInfoMethod = animal.getDeclaredMethod("displayInfo");
        Arrays.stream(animals).forEach(a -> {
            try {
                makeSound.invoke(a);
                displayInfoMethod.invoke(a);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}