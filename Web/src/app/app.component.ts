import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(private translate: TranslateService) {
  }

  ngOnInit(): void {
    const language = localStorage.getItem("language")
    this.translate.use(language ?? 'en-US')

    const theme = localStorage.getItem("theme")
    document.body.classList.add(theme ?? 'dark')
  }
}
