import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Bus } from 'src/app/shared/models/bus';
import { BusesService } from 'src/app/core/buses.service';

@Component({
  selector: 'app-form-buses',
  templateUrl: './form-buses.component.html',
  styleUrls: ['./form-buses.component.css']
})
export class FormBusesComponent implements OnInit {

  formBuses: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: BusesService) { }

  ngOnInit(): void {
    this.createForm(this.createEmptyForm());
  }

  createForm(bus: Bus) {
    this.formBuses = this.formBuilder.group({
      code: [bus.code, [Validators.required]],
      latitude: [bus.latitude, [Validators.required]],
      longitude: [bus.longitude, [Validators.required]],
      line: [bus.line, [Validators.required]]
    });
  }

  createEmptyForm(): Bus {
    return {
      code: null,
      latitude: null,
      longitude: null,
      line: null
    };
  }

  onSubmit() {
    const bus: Bus = this.formBuses.value;
    bus.line = { code: bus.line };

    this.service.save(bus);
  }
}
