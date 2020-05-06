import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Line } from '../shared/models/line';
import { Observable } from 'rxjs';

const url = 'http://localhost:8080/api/lines';

@Injectable({
  providedIn: 'root'
})
export class LinesService {

  constructor(private http: HttpClient) { }

  save(line: Line): Observable<Line> {
    return this.http.post<Line>(url, line);
  }
}
