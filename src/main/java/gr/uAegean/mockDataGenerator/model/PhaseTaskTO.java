package gr.uAegean.mockDataGenerator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PhaseTaskTO {
    private String phase;
    @JsonProperty("task_id")
    private String taskId;
}
