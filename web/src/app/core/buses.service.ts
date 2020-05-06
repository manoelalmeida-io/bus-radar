import { Injectable } from '@angular/core';
import { Bus } from '../shared/models/bus';
import { HttpClient } from '@angular/common/http';

const url = 'http://localhost:8080/api/buses';

@Injectable({
  providedIn: 'root'
})
export class BusesService {

  buses: Array<Bus> = [];

  constructor(private http: HttpClient) { }

  list(): void {
    this.http.get<Array<Bus>>(url).subscribe((buses: Array<Bus>) => {
      this.buses = buses;
    });
  }

  save(bus: Bus): void {
    this.http.post<Bus>(url, bus).subscribe((saved: Bus) => {
      this.buses.push(saved);
    });
  }
}
