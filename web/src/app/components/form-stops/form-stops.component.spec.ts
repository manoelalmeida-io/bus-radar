import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormStopsComponent } from './form-stops.component';

describe('FormStopsComponent', () => {
  let component: FormStopsComponent;
  let fixture: ComponentFixture<FormStopsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormStopsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormStopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
