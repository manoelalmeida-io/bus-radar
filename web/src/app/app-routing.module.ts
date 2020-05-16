import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LinesComponent } from './pages/lines/lines.component';
import { BusesComponent } from './pages/buses/buses.component';
import { StopsComponent } from './pages/stops/stops.component';
import { LineRouteComponent } from './pages/line-route/line-route.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'lines',
    pathMatch: 'full'
  },
  {
    path: 'lines',
    children: [
      {
        path: '',
        component: LinesComponent
      },
      {
        path: ':id/route',
        component: LineRouteComponent
      }
    ]
  },
  {
    path: 'buses',
    component: BusesComponent
  },
  {
    path: 'stops',
    component: StopsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
