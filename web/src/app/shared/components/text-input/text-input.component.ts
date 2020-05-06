import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-text-input',
  templateUrl: './text-input.component.html',
  styleUrls: ['./text-input.component.css']
})
export class TextInputComponent implements OnInit {

  @Input() name: string;
  @Input() formGroup: FormGroup;
  @Input() placeholder: string;

  constructor() { }

  ngOnInit(): void {
  }

}
