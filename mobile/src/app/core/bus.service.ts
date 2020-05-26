import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bus } from '../shared/models/bus';

const nearbyUrl = 'http://192.168.1.34:8080/api/user/nearby';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  constructor(private http: HttpClient) { }

  nearby(): Observable<Array<Bus>> {
    const user = {
      latitude: -23.418323,
	    longitude: -46.717530
    }

    return this.http.post<Array<Bus>>(nearbyUrl, user);
  }
}
