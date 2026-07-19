package ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Suite {

    private String name;
    private String description;
    private String preconditions;
}
