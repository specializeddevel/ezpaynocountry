import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
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
  declarations: [],
  imports: [...SHARED_MODULES],
  exports: [...SHARED_MODULES],
})
export class SharedModule {}