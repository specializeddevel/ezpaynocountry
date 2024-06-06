import { NgModule, Optional, SkipSelf, ErrorHandler } from "@angular/core";
import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
// import { TokenInterceptor } from "./interceptor/jwt-interceptor";
// import { AppErrorInterceptor } from "./interceptor/error-interceptor";

// import { AppInitService } from "./services/app-init.service";
// export function initializerFactory(appConfig: AppInitService) {
//   return (): Promise<any> => {
//     return appConfig.load();
//   };
// }

@NgModule({
  imports: [CommonModule, HttpClientModule],
  providers: [
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: TokenInterceptor,
    //   multi: true,
    // },
    // {
    //   provide: APP_INITIALIZER,
    //   useFactory: initializerFactory,
    //   deps: [AppInitService],
    //   multi: true,
    // },
    // {
    //   provide: ErrorHandler,
    //   useClass: AppErrorInterceptor,
    // },
  ],
  exports: [HttpClientModule],
})
export class CoreModule {
  constructor(
    @Optional()
    @SkipSelf()
    parentModule: CoreModule
  ) {
    if (parentModule) {
      throw new Error("CoreModule is already loaded. Import only in AppModule");
    }
  }
}