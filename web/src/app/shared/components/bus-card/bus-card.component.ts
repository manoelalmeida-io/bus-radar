import { Component, OnInit, Input } from '@angular/core';
import { Bus } from '../../models/bus';

@Component({
  selector: 'app-bus-card',
  templateUrl: './bus-card.component.html',
  styleUrls: ['./bus-card.component.css']
})
export class BusCardComponent implements OnInit {

  @Input() bus: Bus;

  constructor() { }

  ngOnInit(): void {
  }

}
