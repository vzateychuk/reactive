import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Room } from './room';

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
  title = 'reservation-app';
  rooms: Room[];

  private baseUrl: string = 'http://localhost:8080';
  private reservationUrl: string = this.baseUrl + '/room/v1/reservation';

  constructor(private http: HttpClient) {    }

  ngOnInit(): void {
    this.rooms = roomsConst;
    console.log(this.rooms);
  }
}
