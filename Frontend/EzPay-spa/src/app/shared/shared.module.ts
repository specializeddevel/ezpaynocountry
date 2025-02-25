import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NavComponent } from './components/nav/nav.component';
import { ContactUsersComponent } from './components/contact-users/contact-users.component';
// import { BrowserModule } from "@angular/platform-browser";
// import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

// import { SharedDirectivesModule } from "./directives/directives.module";
// import { SharedPipesModule } from "./pipes/pipes.module";
// import { SharedComponentsModule } from "./components/components.module";

const SHARED_MODULES = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule,

  // SharedDirectivesModule,
  // SharedPipesModule,
  // SharedComponentsModule,
];

@NgModule({
  providers: [],
  declarations: [
    NavComponent,
    ContactUsersComponent
  ],
  imports: [...SHARED_MODULES],
  exports: [NavComponent, ContactUsersComponent],
})
export class SharedModule {}