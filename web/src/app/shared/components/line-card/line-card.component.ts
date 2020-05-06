import { Component, OnInit, Input } from '@angular/core';
import { Line } from '../../models/line';

@Component({
  selector: 'app-line-card',
  templateUrl: './line-card.component.html',
  styleUrls: ['./line-card.component.css']
})
export class LineCardComponent implements OnInit {

  @Input() line: Line;

  constructor() { }

  ngOnInit(): void {
  }

}
