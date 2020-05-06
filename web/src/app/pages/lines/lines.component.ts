import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LinesService } from 'src/app/core/lines.service';

import { debounceTime } from 'rxjs/operators'; 
import { Line } from 'src/app/shared/models/line';

@Component({
  selector: 'app-lines',
  templateUrl: './lines.component.html',
  styleUrls: ['./lines.component.css']
})
export class LinesComponent implements OnInit {

  formSearch: FormGroup;
  filterText = '';

  constructor(
    private formBuilder: FormBuilder,
    public service: LinesService) { }

  ngOnInit(): void {
    this.formSearch = this.formBuilder.group({
      search: ['']
    });

    this.formSearch.get('search').valueChanges
      .pipe(debounceTime(400))
      .subscribe((val: string) => {
        this.filterText = val;
      });

    this.listLines();
  }

  listLines(): void {
    this.service.list();
  }

  search(): Array<Line> {

    if (this.filterText != '') {
      return this.service.lines.filter((val: Line) => this.filter(val));
    }

    return this.service.lines;
  }

  filter(line: Line) {
    const filterText = this.filterText.toLowerCase();
    const lineName = line.name.toLowerCase();
    const lineCode = line.code.toLowerCase();

    return lineName.includes(filterText) || lineCode.includes(filterText);
  }
}
