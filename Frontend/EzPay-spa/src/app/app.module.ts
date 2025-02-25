import { SharedModule } from './shared/shared.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './auth/register/register.component';
import { CoreModule } from './core/core.module';
import {HomeComponent}  from './layout/home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import InicioComponent  from './modules/dashboard/pages/inicio/inicio.component';
import { InicioCardComponent } from './modules/dashboard/pages/inicio/components/inicio-card/inicio-card.component';
import { UserComponent } from './modules/user/user.component';
import { CardListComponent } from './modules/dashboard/pages/inicio/components/card-list/card-list.component';


@NgModule({
  declarations: [AppComponent, RegisterComponent, HomeComponent, DashboardComponent, InicioComponent, InicioCardComponent, UserComponent, LoginComponent, CardListComponent ],
  imports: [
    BrowserModule, 
    //features
    //import the dashboard
    // app
    AppRoutingModule, 
    //core and shared modules
    CoreModule, 
    SharedModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
