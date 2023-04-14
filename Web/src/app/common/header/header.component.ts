import {Component} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  get currentTheme() {
    return localStorage.getItem("theme")
  }

  get currentLanguage() {
    return localStorage.getItem("language")
  }

  constructor(private translate: TranslateService) {
  }

  theme(target: HTMLSelectElement): void {
    localStorage.setItem("theme", target.value)
    document.body.classList.remove("light", "dark")
    document.body.classList.add(target.value)
  }

  language(target: HTMLSelectElement) {
    localStorage.setItem("language", target.value)
    this.translate.use(target.value)
  }
}
