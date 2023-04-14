import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {Bus} from "../../models/bus";
import {FavoritesService} from "../../shared/favorites.service";

@Component({
  selector: 'app-buses-list',
  templateUrl: './buses-list.component.html',
  styleUrls: ['./buses-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class BusesListComponent {
  @Input() urlPrefix: string = ""
  @Input() urlSuffix: string = ""
  @Input() buses: Bus[] = []

  constructor(private favoritesService: FavoritesService) {
  }

  toggleFavorite(bus: Bus): void {
    this.favoritesService.toggle("buses", bus)
  }

  isFavorite(bus: Bus): boolean {
    return this.favoritesService.contain("buses", bus)
  }
}
