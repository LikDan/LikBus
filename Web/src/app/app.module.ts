import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { HeaderComponent } from './common/header/header.component';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { FindComponent } from './pages/find/find.component';
import { BusesListComponent } from './components/buses-list/buses-list.component';
import { StopsListComponent } from './components/stops-list/stops-list.component';
import { FindStopsComponent } from './pages/find-stops/find-stops.component';
import { FindBusesComponent } from './pages/find-buses/find-buses.component';
import { RouteComponent } from './pages/route/route.component';
import { MapComponent } from './common/map/map.component';
import {GoogleMapsModule} from "@angular/google-maps";
import { FavoritesComponent } from './pages/favorites/favorites.component';
import { RoutesListComponent } from './components/routes-list/routes-list.component';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FindComponent,
    BusesListComponent,
    StopsListComponent,
    FindStopsComponent,
    FindBusesComponent,
    RouteComponent,
    MapComponent,
    FavoritesComponent,
    RoutesListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    GoogleMapsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
