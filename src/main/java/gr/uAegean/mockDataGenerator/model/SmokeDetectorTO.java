package gr.uAegean.mockDataGenerator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
@ToString
public class SmokeDetectorTO {

    String[] triggered;

}
