package ui.dto;

import com.github.javafaker.Faker;

public class PlanFactory {

    public static Plan getPlan() {
        Faker faker = new Faker();
        return new Plan(faker.lorem().characters(10));
    }
}
