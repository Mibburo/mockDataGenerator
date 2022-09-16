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
public class ShmAlarm {

    private String timestamp;
    private String type;
    @JsonProperty("component_id")
    private String componentId;
    @JsonProperty("accelerometer_x")
    private String accelerometerX;
    @JsonProperty("accelerometer_y")
    private String accelerometerY;
    @JsonProperty("accelerometer_z")
    private String accelerometerZ;
    @JsonProperty("heave_velocity")
    private String heaveVelocity;
    @JsonProperty("heave_acceleration")
    private String heaveAcceleration;
    @JsonProperty("heave_ship_motion")
    private String heaveShipMotion;
    private String yaw;
    private String pitch;
    private String roll;
}
