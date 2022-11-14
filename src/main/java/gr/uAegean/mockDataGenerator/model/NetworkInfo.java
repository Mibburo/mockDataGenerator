package gr.uAegean.mockDataGenerator.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NetworkInfo implements Serializable {


    private List<DeviceInfo> deviceInfoList;
    private String messagingAppClientId;
    private String arGlassesId;
    private String braceletId;


}