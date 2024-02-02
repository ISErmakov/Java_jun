package ru.alina_corp.seminar1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        // Проверка наличия белков, жиров и углеводов в пищевых продуктах
        AtomicBoolean proteins = new AtomicBoolean(foodstuffs.stream().anyMatch(Food::getProteins));
        AtomicBoolean fats = new AtomicBoolean(foodstuffs.stream().anyMatch(Food::getFats));
        AtomicBoolean carbohydrates = new AtomicBoolean(foodstuffs.stream().anyMatch(Food::getCarbohydrates));

        // Если все три компонента уже присутствуют в корзине, выводится сообщение
        // "Корзина уже сбалансирована по БЖУ"
        if (proteins.get() && fats.get() && carbohydrates.get())
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        // Добавление продуктов из магазина, чтобы сбалансировать корзину по БЖУ
        market.getThings(clazz).stream()
                .filter(thing -> !proteins.get() && thing.getProteins())
                .findFirst()
                .ifPresent(thing -> {
                    proteins.set(true);
                    foodstuffs.add(thing);
                });

        market.getThings(clazz).stream()
                .filter(thing -> !fats.get() && thing.getFats())
                .findFirst()
                .ifPresent(thing -> {
                    fats.set(true);
                    foodstuffs.add(thing);
                });

        market.getThings(clazz).stream()
                .filter(thing -> !carbohydrates.get() && thing.getCarbohydrates())
                .findFirst()
                .ifPresent(thing -> {
                    carbohydrates.set(true);
                    foodstuffs.add(thing);
                });

        // Проверка, удалось ли сбалансировать корзину по БЖУ
        if (proteins.get() && fats.get() && carbohydrates.get()) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs(){
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");
         */
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));

    }

}
