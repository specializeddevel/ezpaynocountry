import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './layout/home/home.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import InicioComponent from './modules/dashboard/pages/inicio/inicio.component';
import { UserComponent } from './modules/user/user.component';

 

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
        path: '',
        redirectTo: 'inicio',
        pathMatch: 'full'
      },
      {
        path: 'inicio',
        component: InicioComponent,
        // loadComponent: () =>
        //   import('./modules/dashboard/pages/inicio/inicio.component'),
      },
      // {
      //   path:'card'
      // }
       {
        path:'user',
        component: UserComponent
      }
    
    
     
      // {path:'inicio', loadComponent:()=> import()},
    ],
  },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  // Fallback when no prior routes is matched or 404
  { path: '**', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
