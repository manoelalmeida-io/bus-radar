import { Component, OnInit, Input } from '@angular/core';
import { Stop } from '../../models/stop';

@Component({
  selector: 'app-stop-card',
  templateUrl: './stop-card.component.html',
  styleUrls: ['./stop-card.component.css']
})
export class StopCardComponent implements OnInit {

  @Input() stop: Stop;

  constructor() { }

  ngOnInit(): void {
  }

}
