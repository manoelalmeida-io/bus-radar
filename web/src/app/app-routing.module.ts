import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LinesComponent } from './pages/lines/lines.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'lines',
    pathMatch: 'full'
  },
  {
    path: 'lines',
    component: LinesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
