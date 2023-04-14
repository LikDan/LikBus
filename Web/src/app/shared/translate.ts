import {TranslateLoader} from "@ngx-translate/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

export class Translate implements TranslateLoader {
  constructor(private http: HttpClient) {
  }

  getTranslation(lang: string): Observable<Object> {
    return this.http.get(`api/internalization?locale=${lang}`)
  }
}
