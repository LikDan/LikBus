import {Component, OnInit} from '@angular/core';
import {mergeMap, Observable} from "rxjs";
import {FullBus} from "../../models/bus";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {RouteStopSchedule} from "../../models/rotutes";
import {FavoritesService} from "../../shared/favorites.service";
import {Stop} from "../../models/stop";

@Component({
  selector: 'page-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.scss']
})
export class RouteComponent implements OnInit {
  route$!: Observable<RouteStopSchedule>

  constructor(private http: HttpClient, private route: ActivatedRoute, private favoritesService: FavoritesService) {
  }

  ngOnInit(): void {
    this.route$ = this.route.params.pipe(mergeMap(p => this.http.get<RouteStopSchedule>(`/api/stops/${p['stopID']}/buses/${p['busID']}/schedule`)))
  }

  toggleFavorite(route: RouteStopSchedule): void {
    this.favoritesService.toggle("routes", route)
  }

  isFavorite(route: RouteStopSchedule): boolean {
    return this.favoritesService.contain("routes", route)
  }
}
