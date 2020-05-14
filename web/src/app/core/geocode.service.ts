import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const url = 'https://geocode.xyz';

@Injectable({
  providedIn: 'root'
})
export class GeocodeService {

  constructor(private http: HttpClient) { }

  geolocationToAddress(lat: number, lon: number): Observable<any> {
    return this.http.get(`${url}/${lat},${lon}/?geoit=json`);
  }
}
