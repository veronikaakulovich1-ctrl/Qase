package ui.dict;

import java.util.concurrent.ThreadLocalRandom;

public enum CaseStatus {
    ACTUAL("Actual"),
    DRAFT("Draft"),
    DEPRECATED("Deprecated");

    private final String uiText;

    CaseStatus(String uiText) {
        this.uiText = uiText;
    }

    public String getUiText() {
        return uiText;
    }

    public static CaseStatus random() {
        CaseStatus[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
