import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  @Input() text: string;
  @Input() type: string;
  @Output() action = new EventEmitter<any>();

  constructor() { }

  ngOnInit(): void {
  }

  onClick(event: any) {
    this.action.emit(event);
  }
}
