package ui.dto;

import com.github.javafaker.Faker;
import ui.dict.CaseStatus;
import ui.dict.CaseType;
import ui.dict.Priority;
import ui.dict.Severity;

public class CaseFactory {

    public static Case getCase() {
        Faker faker = new Faker();
        return new Case(
                faker.lorem().characters(10),
                CaseStatus.random(),
                Severity.random(),
                Priority.random(),
                CaseType.random()
        );
    }
}
