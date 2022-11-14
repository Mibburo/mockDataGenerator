package gr.uAegean.mockDataGenerator.model;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo implements Serializable {
    private List<UserGeofenceUnit> geofenceHistory;
    private List<UserLocationUnit> locationHistory;
    private String speed;
}
