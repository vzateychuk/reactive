import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private baseUrl: string = 'http://localhost:8080';
  private reservationUrl: string = this.baseUrl + '/room/v1/reservation';

  constructor(private http: HttpClient) { }

  createReservation(body: ReservationRequest): Observable<Reservation> {
    let httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    return this.http.post<Reservation>(this.reservationUrl, body, httpOptions);
  }

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.reservationUrl);
  }
}

export class ReservationRequest {
  constructor(
    private roomNumber: number,
    private checkIn: string,
    private checkOut: string,
    private price: number
  ) {}
}

export interface Reservation {
  id: string;
  roomNumber: number;
  checkIn: Date;
  checkOut: Date;
  price: number;
}