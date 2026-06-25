package dto;

import com.github.javafaker.Faker;

public class ProjectFactory {

    public static Project getProject() {
        Faker faker = new Faker();
        String code = "QASE" + faker.number().numberBetween(1, 1_000_000);
        return new Project(
                faker.company().name(),
                code
        );
    }
}
