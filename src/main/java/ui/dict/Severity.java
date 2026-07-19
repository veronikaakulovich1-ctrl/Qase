package ui.dict;

import java.util.concurrent.ThreadLocalRandom;

public enum Severity {
    BLOCKER("Blocker"),
    CRITICAL("Critical"),
    MAJOR("Major"),
    NORMAL("Normal"),
    MINOR("Minor"),
    TRIVIAL("Trivial");

    private final String uiText;

    Severity(String uiText) {
        this.uiText = uiText;
    }

    public String getUiText() {
        return uiText;
    }

    public static Severity random() {
        Severity[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
