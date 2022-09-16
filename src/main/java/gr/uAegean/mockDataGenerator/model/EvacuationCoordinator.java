package gr.uAegean.mockDataGenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvacuationCoordinator {

    private String originator;
    @JsonProperty("evacuation-status")
    private String evacStatus;
    private String timestamp;
}
