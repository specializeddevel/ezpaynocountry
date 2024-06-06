import { providerHttpClient } from '@angular/common/http';
import { ApplicationConfig } from '@angular/core';

export const appConfig = new ApplicationConfig = {
  providers: [providerHttpClient()],
};
