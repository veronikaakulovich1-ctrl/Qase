package ui.dict;

import java.util.concurrent.ThreadLocalRandom;

public enum Priority {
    NOT_SET("Not set"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String uiText;

    Priority(String uiText) {
        this.uiText = uiText;
    }

    public String getUiText() {
        return uiText;
    }

    public static Priority random() {
        Priority[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
