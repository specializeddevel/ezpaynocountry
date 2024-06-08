import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './layout/home/home.component';
import { RegisterComponent } from './auth/register/register.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full',
  },
  { path: 'home', component: HomeComponent },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: 'inicio',
        loadComponent: () =>
          import('./modules/dashboard/pages/inicio/inicio.component'),
      },
      // {path:'inicio', loadComponent:()=> import()},
    ],
  },
  { path: 'register', component: RegisterComponent },

  // Fallback when no prior routes is matched or 404
  { path: '**', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
