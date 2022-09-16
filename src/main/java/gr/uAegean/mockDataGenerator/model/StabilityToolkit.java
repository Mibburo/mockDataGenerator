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
public class StabilityToolkit {

    private String state;
    private String timestamp;
    private String fn;
    private String t;
    @JsonProperty("Hs")
    private String hs;
    @JsonProperty("L0")
    private String l0;
    private String beta;
    private String head;
    @JsonProperty("Surge")
    private String surge;
    @JsonProperty("Sway")
    private String sway;
    @JsonProperty("Heave")
    private String heave;
    @JsonProperty("Roll")
    private String roll;
    @JsonProperty("Pitch")
    private String pitch;
    @JsonProperty("Yaw")
    private String yaw;
}
