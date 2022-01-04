package pl.pjwstk.projekt.backend.services.data;

import lombok.Data;

import java.util.Map;

@Data
public class Reserve {
    private SeatReservation seatReservation;
    private Map<String, Boolean> map;
}
