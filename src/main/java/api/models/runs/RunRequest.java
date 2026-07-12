package api.models.runs;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RunRequest {
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private List<Integer> cases;
    @Expose
    private Integer plan_id;
    @Expose
    private Boolean is_autotest;
    @Expose
    private Boolean include_all_cases;
}
