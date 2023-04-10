import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FindComponent} from "./pages/find/find.component";
import {FindBusesComponent} from "./pages/find-buses/find-buses.component";
import {FindStopsComponent} from "./pages/find-stops/find-stops.component";
import {RouteComponent} from "./pages/route/route.component";
import {FavoritesComponent} from "./pages/favorites/favorites.component";

const routes: Routes = [
  {path: "find/stops", component: FindComponent},
  {path: "find/buses", component: FindComponent},
  {path: "find/stops/:id/buses", component: FindBusesComponent},
  {path: "find/buses/:id/stops", component: FindStopsComponent},
  {path: "find/stops/:stopID/buses/:busID/schedule", component: RouteComponent},
  {path: "find/buses/:busID/stops/:stopID/schedule", component: RouteComponent},

  {path: "favorites/buses", component: FavoritesComponent},
  {path: "favorites/stops", component: FavoritesComponent},
  {path: "favorites/routes", component: FavoritesComponent},


  {path: "", pathMatch: 'full', redirectTo: "find/buses"},
  {path: "find", pathMatch: 'full', redirectTo: 'find/buses'},
  {path: "find/stops/:id", pathMatch: 'full', redirectTo: 'find/stops/:id/buses'},
  {path: "find/buses/:id", pathMatch: 'full', redirectTo: 'find/buses/:id/stops'},
  {path: "find/stops/:stopID/buses/:busID", pathMatch: 'full', redirectTo: 'find/stops/:stopID/buses/:busID/schedule'},
  {path: "find/buses/:busID/stops/:stopID", pathMatch: 'full', redirectTo: 'find/buses/:busID/stops/:stopID/schedule'},

  {path: "favorites", pathMatch: 'full', redirectTo: "favorites/buses"},


  {path: "**", pathMatch: 'full', redirectTo: "find/buses"},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
