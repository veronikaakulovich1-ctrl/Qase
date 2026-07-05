package ui.dto;

import com.github.javafaker.Faker;

public class UserFactory {

    public static User getUser() {
        Faker faker = new Faker();
        String password = faker.internet().password(12, 16, true, true, true);
        String email = faker.letterify("????????").toLowerCase() + "@mailto.plus";
        return new User(email, password);
    }
}
