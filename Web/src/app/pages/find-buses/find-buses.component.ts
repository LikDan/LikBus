import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {mergeMap, Observable} from "rxjs";
import {FullStop} from "../../models/stop";

@Component({
  selector: 'app-find-buses',
  templateUrl: './find-buses.component.html',
  styleUrls: ['./find-buses.component.scss']
})
export class FindBusesComponent implements OnInit {
  stop$!: Observable<FullStop>

  constructor(private http: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.stop$ = this.route.params.pipe(mergeMap(p => this.http.get<FullStop>(`/api/stops/${p['id']}/buses`)))
  }
}
