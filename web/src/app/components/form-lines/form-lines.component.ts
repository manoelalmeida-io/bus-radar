import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Line } from 'src/app/shared/models/line';
import { LinesService } from 'src/app/core/lines.service';

@Component({
  selector: 'app-form-lines',
  templateUrl: './form-lines.component.html',
  styleUrls: ['./form-lines.component.css']
})
export class FormLinesComponent implements OnInit {

  formLines: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private service: LinesService) { }

  ngOnInit(): void {
    this.createForm(this.createEmptyForm());
  }

  createForm(line: Line) {
    this.formLines = this.formBuilder.group({
      code: [line.code],
      name: [line.name],
      forward: [line.forward],
      backward: [line.backward],
      color: [line.color]
    });
  }

  createEmptyForm(): Line {
    return {
      code: null,
      name: null,
      forward: null,
      backward: null,
      color: null
    }
  }

  onSubmit() {
    this.service.save(this.formLines.value).subscribe();
  }
}
