import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, mergeMap, Observable, tap} from "rxjs";
import {Stop} from "../../models/stop";
import {Bus} from "../../models/bus";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'page-find',
  templateUrl: './find.component.html',
  styleUrls: ['./find.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FindComponent implements OnInit {
  items$!: Observable<Bus[] | Stop[]>
  listType$!: Observable<string>

  constructor(private http: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.listType$ = this.route.url.pipe(map(s => s[1].path))
    this.items$ = this.listType$.pipe(mergeMap(s => this.http.get<Bus[] | Stop[]>(`/api/${s}`)))
  }
}
