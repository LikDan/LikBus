import {Component, Input} from '@angular/core';
import {Stop} from "../../models/stop";
import {FavoritesService} from "../../shared/favorites.service";
import {Bus} from "../../models/bus";

@Component({
  selector: 'app-stops-list',
  templateUrl: './stops-list.component.html',
  styleUrls: ['./stops-list.component.scss']
})
export class StopsListComponent {
  @Input() urlPrefix: string = ""
  @Input() urlSuffix: string = ""
  @Input() stops: Stop[] = []

  constructor(private favoritesService: FavoritesService) {
  }

  toggleFavorite(stop: Stop): void {
    this.favoritesService.toggle("stops", stop)
  }

  isFavorite(stop: Stop): boolean {
    return this.favoritesService.contain("stops", stop)
  }
}
