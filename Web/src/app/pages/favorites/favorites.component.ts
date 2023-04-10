import {Component, OnInit} from '@angular/core';
import {FavoritesService} from "../../shared/favorites.service";
import {map, mergeMap, Observable} from "rxjs";
import {Bus} from "../../models/bus";
import {Stop} from "../../models/stop";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {RouteStopSchedule} from "../../models/rotutes";

@Component({
  selector: 'page-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.scss']
})
export class FavoritesComponent implements OnInit {
  items$!: Observable<Bus[] | Stop[] | RouteStopSchedule[]>
  listType$!: Observable<string>

  constructor(private http: HttpClient, private route: ActivatedRoute, private service: FavoritesService) {
  }

  ngOnInit(): void {
    this.listType$ = this.route.url.pipe(map(s => s[1].path))
    this.items$ = this.listType$.pipe(map(s => this.service.getByName(s))) as any
  }

  get items() {
    return this.service.items
  }
}
