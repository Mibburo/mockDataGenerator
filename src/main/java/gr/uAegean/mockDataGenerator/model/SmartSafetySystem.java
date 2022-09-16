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
public class SmartSafetySystem {

    private String type;
    private String timestamp;
    private String deck;
    @JsonProperty("position_x")
    private String positionX;
    @JsonProperty("position_y")
    private String positionY;
}
