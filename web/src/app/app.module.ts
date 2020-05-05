import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LinesComponent } from './pages/lines/lines.component';
import { FormLinesComponent } from './components/form-lines/form-lines.component';
import { TextInputComponent } from './shared/components/text-input/text-input.component';
import { ButtonComponent } from './shared/components/button/button.component';
import { LineCardComponent } from './shared/components/line-card/line-card.component';

@NgModule({
  declarations: [
    AppComponent,
    LinesComponent,
    FormLinesComponent,
    TextInputComponent,
    ButtonComponent,
    LineCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
