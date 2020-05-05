import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-lines',
  templateUrl: './form-lines.component.html',
  styleUrls: ['./form-lines.component.css']
})
export class FormLinesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  alerta() {
    alert('asdf');
  }

}
