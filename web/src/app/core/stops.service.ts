import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Stop } from '../shared/models/stop';

const url = 'http://localhost:8080/api/stops';

@Injectable({
  providedIn: 'root'
})
export class StopsService {

  stops: Array<Stop> = [];

  constructor(private http: HttpClient) { }

  list(): void {
    this.http.get<Array<Stop>>(url).subscribe((stops: Array<Stop>) => {
      this.stops = stops;
    });
  }

  save(stop: Stop): void {
    this.http.post<Stop>(url, stop).subscribe((saved: Stop) => {
      this.stops.push(saved);
    });
  }
}
