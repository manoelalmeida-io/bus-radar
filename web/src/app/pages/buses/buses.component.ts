import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { BusesService } from 'src/app/core/buses.service';
import { Bus } from 'src/app/shared/models/bus';

import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-buses',
  templateUrl: './buses.component.html',
  styleUrls: ['./buses.component.css']
})
export class BusesComponent implements OnInit {

  formSearch: FormGroup;
  filterText = '';

  constructor(
    private formBuilder: FormBuilder,
    public service: BusesService) { }

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

  search(): Array<Bus> {
    if (this.filterText !== '') {
      return this.service.buses.filter((bus: Bus) => this.filter(bus));
    }

    return this.service.buses;
  }

  filter(bus: Bus) {
    const filterText = this.filterText.toLowerCase();
    const code = bus.code.toLowerCase();
    const line: string = bus.line.code.toLowerCase();

    return code.includes(filterText) || line.includes(filterText);
  }
}
