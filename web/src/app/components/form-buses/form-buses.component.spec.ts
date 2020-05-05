import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormBusesComponent } from './form-buses.component';

describe('FormBusesComponent', () => {
  let component: FormBusesComponent;
  let fixture: ComponentFixture<FormBusesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormBusesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormBusesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
