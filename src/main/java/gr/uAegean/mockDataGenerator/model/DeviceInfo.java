package gr.uAegean.mockDataGenerator.model;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo implements Serializable {

    private String macAddress;
    private String hashedMacAddress;
    private String imsi;
    private String msisdn;
    private String imei;
    private  String braceletId;


}