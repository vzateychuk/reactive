import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup} from '@angular/forms';
import { Room } from './room';
import { ReservationService, ReservationRequest, Reservation } from './reservation.service';

const roomsConst = [
  new Room("123", "123", "150"),
  new Room("127", "127", "180"),
  new Room("238", "238", "201"),
]

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  rooms: Room[];

  roomSearchForm: FormGroup;
  currentCheckInVal: string;
  currentCheckOutVal: string;
  currentRoomNumber: number;
  currentPrice: number;
  currentReservations: Reservation[];

  constructor(private reservationService: ReservationService) { }

  ngOnInit(): void {

    this.roomSearchForm = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl(''),
      roomNumber: new FormControl('')
    });

    this.roomSearchForm.valueChanges.subscribe(form => {
      this.currentCheckInVal = form.checkin;
      this.currentCheckOutVal = form.checkout;
        // roomValues in format of: roomNumber|price (see html)
      let roomVals: string[] = form.roomNumber.split('|');
      this.currentRoomNumber = Number(roomVals[0]);
      this.currentPrice = +roomVals[1];
    });

    this.rooms = roomsConst;

    this.getReservations();
  }

  createReservation() {
    this.reservationService.createReservation(
      new ReservationRequest(
        this.currentRoomNumber,
        this.currentCheckInVal,
        this.currentCheckOutVal,
        this.currentPrice
      )
    ).subscribe(
      postResult => console.log(postResult)
    );
  }

  getReservations() {
    this.reservationService.getReservations()
      .subscribe(result => {
        console.log(result);
        this.currentReservations = result as Reservation[]
      })
  }
}
