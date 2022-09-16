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
public class Weather {

    @JsonProperty("Timestamp")
    private String timestamp;
    private String combDirectionDegrees;
    private String combPeriod;
    private String combSWHMeters;
    private String currentsDirectionDegrees;
    private String currentsSpeedKnots;
    @JsonProperty("hum%")
    private String humPercentage;
    private String iceCover;
    private String lat;
    private String lon;
    private String mslhPa;
    private String sea;
    private String swellDirectionMeters;
    private String swellPeriod;
    private String swellSWHMeters;
    private String tempCelciousDegrees;
    private String visKm;
    private String wavesDirectionDegrees;
    private String wavesPeriod;
    private String wavesSWHMeters;
    private String windDirectionDegrees;
    private String windSpeedKnots;
}
