package org.example;

import com.github.javafaker.Faker;

public class DataFaker {

    static Faker faker = new Faker();

    public static String getRandomName() {
        return faker.name().name();

    }
    public static String getRandomMail(){
        return faker.internet().emailAddress();
    }
    public static String getRandomUrl() {

        String randomPath = faker.bothify("??????"); // e.g., "abcxyz" (random 6 characters)
        String randomSubPath = faker.bothify("??????"); // e.g., "defuvw" (random 6 characters)

        // Combine into a URL
        String randomUrl = "http://" + randomPath + "/" + randomSubPath;
        return randomUrl;
    }
}
