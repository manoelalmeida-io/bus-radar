import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Stop } from 'src/app/shared/models/stop';
import { StopsService } from 'src/app/core/stops.service';

@Component({
  selector: 'app-form-stops',
  templateUrl: './form-stops.component.html',
  styleUrls: ['./form-stops.component.css']
})
export class FormStopsComponent implements OnInit {

  formStops: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: StopsService) { }

  ngOnInit(): void {
    this.createForm(this.createEmptyForm());
  }

  createForm(stop: Stop): void {
    this.formStops = this.formBuilder.group({
      code: [stop.code, [Validators.required]],
      latitude: [stop.latitude, [Validators.required]],
      longitude: [stop.longitude, [Validators.required]]
    });
  }

  createEmptyForm(): Stop {
    return {
      code: null,
      latitude: null,
      longitude: null
    };
  }

  onSubmit() {
    this.formStops.markAllAsTouched();

    const stop: Stop = this.formStops.value;

    if (!this.formStops.invalid) {
      this.service.save(stop);
    }
  }
}
