package vez.reactive.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import vez.reactive.model.Reservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {

    @Autowired private ApplicationContext context;
    private WebTestClient webTestClient;
    private Reservation reservation;

    @Before
    public void setUp() throws Exception {
        webTestClient = WebTestClient.bindToApplicationContext(this.context).build();
        reservation = new Reservation(
                123L,
                LocalDate.now(),
                LocalDate.now().plus(10, ChronoUnit.DAYS),
                139
        );
    }

    @Test
    public void getReservationById() {
        webTestClient.get()
                .uri(ReservationController.ROOM_V1_RESERVATION+"/123")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Reservation.class);
    }

    @Test
    public void getAllReservation() {
        webTestClient.get()
                .uri(ReservationController.ROOM_V1_RESERVATION)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class);
    }

    @Test
    public void createReservation() {
        webTestClient.post()
                .uri(ReservationController.ROOM_V1_RESERVATION)
                .body(Mono.just(reservation), Reservation.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Reservation.class);
    }

    @Test
    public void updatePrice() {
        webTestClient.put()
                .uri(ReservationController.ROOM_V1_RESERVATION+"/123")
                .body(Mono.just(reservation), Reservation.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Reservation.class);
    }

    @Test
    public void deleteReservation() {
        webTestClient.delete()
                .uri(ReservationController.ROOM_V1_RESERVATION+"/123")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Boolean.class);
    }

}
