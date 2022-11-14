package gr.uAegean.mockDataGenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegacySystem {

    private String mitigationSystemActivated;
    private String adjacentDetectors;
    private String containmentDoorsClosed;
    private String Gzgm;
    private String internalCommunication;
    private String externalCommunication;
    private String propulsionSystem;
    private String steeringSystem;
    private String presenceHazards;
    private String statusPA;
    private String statusWay;
    private String proximityAssistance;
    private String navigationalSystem;
    private String deck;
}
