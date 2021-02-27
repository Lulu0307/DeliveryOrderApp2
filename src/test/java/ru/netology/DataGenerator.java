package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DataGenerator {

    private DataGenerator() {
    }

    public static String getDeliveryDate(int days) {
        LocalDate date = LocalDate.now();
        return date.plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getRandomCity() {
        List<String> cities = Arrays.asList("Москва", "Казань", "Кемерово", "Владивосток", "Санкт-Петербург", "Орёл",
                "Новосибирск", "Красноярск");
        Collections.shuffle(cities);
        return cities.get(1);
    }

    public static String getRandomName(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getRandomPhoneNumber(String locale) {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();

    }

    public static RegistrationData generateData() {
        return new RegistrationData(getRandomCity(),
                getDeliveryDate(3),
                getRandomName("ru"),
                getRandomPhoneNumber(
                "ru"));
    }
}
