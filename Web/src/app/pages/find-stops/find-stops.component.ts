import {Component, OnInit} from '@angular/core';
import {mergeMap, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {FullBus} from "../../models/bus";

@Component({
  selector: 'page-find-stops',
  templateUrl: './find-stops.component.html',
  styleUrls: ['./find-stops.component.scss']
})
export class FindStopsComponent implements OnInit {
  bus$!: Observable<FullBus>

  constructor(private http: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.bus$ = this.route.params.pipe(mergeMap(p => this.http.get<FullBus>(`/api/buses/${p['id']}/stops`)))
  }
}
