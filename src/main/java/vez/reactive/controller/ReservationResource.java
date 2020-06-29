package vez.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import vez.reactive.model.Reservation;


@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@CrossOrigin
public class ReservationResource {
    public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation";

    @GetMapping(path = "{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<String> getReservationById(@PathVariable String roomId) {
        // reservationService.getReservation(roomId);
        return Mono.just("{}");
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<String> createReservation(@RequestBody Mono<Reservation> reservation) {
        return Mono.just("{}");
    }

    @PutMapping(path = "{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<String> updatePrice(@PathVariable String roomId, @RequestBody Mono<Reservation> reservation) {
        return Mono.just("{}");
    }

    @DeleteMapping(path = "{roomId}")
    public Mono<Boolean> deleteReservation(@PathVariable String roomId) {
        return Mono.just(true);
    }

}
