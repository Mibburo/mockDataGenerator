package gr.uAegean.mockDataGenerator.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DutySchedule {
    private LocalDateTime dutyStartDateTime;
    private LocalDateTime dutyEndDateTime;
}
