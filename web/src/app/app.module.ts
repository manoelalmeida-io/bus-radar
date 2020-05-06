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
    BusCardComponent
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
