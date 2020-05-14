import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LinesComponent } from './pages/lines/lines.component';
import { FormLinesComponent } from './components/form-lines/form-lines.component';
import { TextInputComponent } from './shared/components/text-input/text-input.component';
import { ButtonComponent } from './shared/components/button/button.component';
import { LineCardComponent } from './shared/components/line-card/line-card.component';
import { BusesComponent } from './pages/buses/buses.component';
import { FormBusesComponent } from './components/form-buses/form-buses.component';
import { BusCardComponent } from './shared/components/bus-card/bus-card.component';
import { HttpClientModule } from '@angular/common/http';
import { StopsComponent } from './pages/stops/stops.component';
import { FormStopsComponent } from './components/form-stops/form-stops.component';
import { StopCardComponent } from './shared/components/stop-card/stop-card.component';

@NgModule({
  declarations: [
    AppComponent,
    LinesComponent,
    FormLinesComponent,
    TextInputComponent,
    ButtonComponent,
    LineCardComponent,
    BusesComponent,
    FormBusesComponent,
    BusCardComponent,
    StopsComponent,
    FormStopsComponent,
    StopCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
