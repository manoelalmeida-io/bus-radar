import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Line } from 'src/app/shared/models/line';
import { LinesService } from 'src/app/core/lines.service';

@Component({
  selector: 'app-form-lines',
  templateUrl: './form-lines.component.html',
  styleUrls: ['./form-lines.component.css']
})
export class FormLinesComponent implements OnInit {

  formLines: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: LinesService) { }

  ngOnInit(): void {
    this.createForm(this.createEmptyForm());
  }

  createForm(line: Line) {
    this.formLines = this.formBuilder.group({
      code: [line.code, [Validators.required]],
      name: [line.name, [Validators.required]],
      forward: [line.forward, [Validators.required]],
      backward: [line.backward, [Validators.required]],
      color: [line.color, [Validators.required]]
    });
  }

  createEmptyForm(): Line {
    return {
      code: null,
      name: null,
      forward: null,
      backward: null,
      color: null
    };
  }

  onSubmit() {
    this.formLines.markAllAsTouched();

    if (!this.formLines.invalid) {
      this.service.save(this.formLines.value);
    }
  }
}
