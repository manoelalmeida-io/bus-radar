import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LinesComponent } from './pages/lines/lines.component';
import { BusesComponent } from './pages/buses/buses.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'lines',
    pathMatch: 'full'
  },
  {
    path: 'lines',
    component: LinesComponent
  },
  {
    path: 'buses',
    component: BusesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
