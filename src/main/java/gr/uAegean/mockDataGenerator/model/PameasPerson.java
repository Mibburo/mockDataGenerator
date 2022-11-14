package gr.uAegean.mockDataGenerator.model;


import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PameasPerson implements Serializable {

    private String id;

    Personalinfo personalInfo;

    NetworkInfo networkInfo;

    LocationInfo locationInfo;
}
