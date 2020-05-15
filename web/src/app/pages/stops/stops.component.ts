import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { StopsService } from 'src/app/core/stops.service';
import { Stop } from 'src/app/shared/models/stop';

import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-stops',
  templateUrl: './stops.component.html',
  styleUrls: ['./stops.component.css']
})
export class StopsComponent implements OnInit {

  formSearch: FormGroup;
  filterText = '';

  constructor(
    private formBuilder: FormBuilder,
    public service: StopsService) { }

  ngOnInit(): void {
    this.formSearch = this.formBuilder.group({
      search: ['']
    });

    this.formSearch.get('search').valueChanges
      .pipe(debounceTime(400))
      .subscribe((val: string) => {
        this.filterText = val;
      });

    this.service.list();
  }

  search(): Array<Stop> {
    if (this.filterText !== '') {
      return this.service.stops.filter((stop: Stop) => this.filter(stop));
    }

    return this.service.stops;
  }

  filter(stop: Stop) {
    const filterText = this.filterText.toLowerCase();
    const code = stop.code.toLowerCase();
    const address = stop.address.toLowerCase();

    return code.includes(filterText) || address.includes(filterText);
  }
}
