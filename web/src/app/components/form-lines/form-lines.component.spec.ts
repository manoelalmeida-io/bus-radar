import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormLinesComponent } from './form-lines.component';

describe('FormLinesComponent', () => {
  let component: FormLinesComponent;
  let fixture: ComponentFixture<FormLinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormLinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormLinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
