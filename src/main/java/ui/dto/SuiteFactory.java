package ui.dto;

import com.github.javafaker.Faker;

public class SuiteFactory {

    public static Suite getSuite() {
        Faker faker = new Faker();
        return new Suite(faker.lorem().characters(8), null, null);
    }

    public static Suite getFullSuite() {
        Faker faker = new Faker();
        return new Suite(
                faker.lorem().characters(8),
                faker.lorem().sentence(),
                faker.lorem().sentence()
        );
    }
}
