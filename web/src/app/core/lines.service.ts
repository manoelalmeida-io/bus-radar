import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Line } from '../shared/models/line';
import { Observable } from 'rxjs';

const url = 'http://localhost:8080/api/lines';

@Injectable({
  providedIn: 'root'
})
export class LinesService {

  lines: Array<Line> = [];

  constructor(private http: HttpClient) { }

  list(): void {
    this.http.get<Array<Line>>(url).subscribe((lines: Array<Line>) => {
      this.lines = lines;
    });
  }

  save(line: Line): void {
    this.http.post<Line>(url, line).subscribe((saved: Line) => {
      this.lines.push(saved);
    });
  }
}
