package api.dict;

public enum ResultStatus {
    PASSED("passed"),
    FAILED("failed"),
    BLOCKED("blocked"),
    SKIPPED("skipped"),
    INVALID("invalid");

    private final String value;

    ResultStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
