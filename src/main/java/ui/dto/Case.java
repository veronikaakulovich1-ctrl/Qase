package ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ui.dict.CaseStatus;
import ui.dict.CaseType;
import ui.dict.Priority;
import ui.dict.Severity;

@Data
@AllArgsConstructor
public class Case {

    private String title;
    private CaseStatus status;
    private Severity severity;
    private Priority priority;
    private CaseType type;
}
