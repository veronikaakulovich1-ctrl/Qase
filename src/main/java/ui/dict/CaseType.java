package ui.dict;

import java.util.concurrent.ThreadLocalRandom;

public enum CaseType {
    OTHER("Other"),
    FUNCTIONAL("Functional"),
    SMOKE("Smoke"),
    REGRESSION("Regression"),
    SECURITY("Security"),
    PERFORMANCE("Performance"),
    ACCEPTANCE("Acceptance");

    private final String uiText;

    CaseType(String uiText) {
        this.uiText = uiText;
    }

    public String getUiText() {
        return uiText;
    }

    public static CaseType random() {
        CaseType[] values = values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
