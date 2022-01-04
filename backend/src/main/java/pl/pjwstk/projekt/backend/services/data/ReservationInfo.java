package pl.pjwstk.projekt.backend.services.data;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationInfo {
    private Long id;
    private Long movieId;
    private LocalTime time;
    private LocalDate date;
    private String title;
    private String name;
    private String surname;
    private String email;
    private String phone;
}
