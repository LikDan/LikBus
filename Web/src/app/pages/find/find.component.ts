import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, mergeMap, Observable, of, zip} from "rxjs";
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
  busTypes$!: Observable<string[] | undefined>

  listType$!: Observable<string>
  busType$!: Observable<string | undefined>
  items$!: Observable<Bus[] | Stop[]>

  constructor(private http: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.busTypes$ = this.http.get<string[]>(`/api/buses/types`)

    this.listType$ = this.route.url.pipe(map(s => s[1].path))
    this.busType$ = this.route.params.pipe(map(s => s['type']))

    this.items$ = zip(this.listType$, this.busType$).pipe(mergeMap(s => this.http.get<Bus[] | Stop[]>(`/api/${s[0]}?type=${s[1] ?? ""}`)))
  }
}
