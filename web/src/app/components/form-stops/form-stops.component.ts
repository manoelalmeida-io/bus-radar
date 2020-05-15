import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Stop } from 'src/app/shared/models/stop';
import { StopsService } from 'src/app/core/stops.service';

import { debounceTime } from 'rxjs/operators';
import { GeocodeService } from 'src/app/core/geocode.service';

@Component({
  selector: 'app-form-stops',
  templateUrl: './form-stops.component.html',
  styleUrls: ['./form-stops.component.css']
})
export class FormStopsComponent implements OnInit {

  formStops: FormGroup;
  latitude = '';
  longitude = '';

  constructor(
    private formBuilder: FormBuilder,
    private service: StopsService,
    private geoService: GeocodeService) { }

  ngOnInit(): void {
    this.createForm(this.createEmptyForm());

    this.formStops.valueChanges
      .pipe(debounceTime(400))
      .subscribe(({latitude, longitude}) => {
        const latValid = (latitude !== null && latitude !== '' && latitude !== this.latitude);
        const lonValid = (longitude !== null && longitude !== '' && longitude !== this.longitude);

        if (latValid && lonValid) {
          this.latitude = latitude;
          this.longitude = longitude;

          this.geoService.geolocationToAddress(latitude, longitude).subscribe(response => {
            this.formStops.get('address').setValue(response.staddress);
          });
        }
      });
  }

  createForm(stop: Stop): void {
    this.formStops = this.formBuilder.group({
      code: [stop.code, [Validators.required]],
      latitude: [stop.latitude, [Validators.required]],
      longitude: [stop.longitude, [Validators.required]],
      address: [stop.address, [Validators.required]],
    });
  }

  createEmptyForm(): Stop {
    return {
      code: null,
      latitude: null,
      longitude: null,
      address: null
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
