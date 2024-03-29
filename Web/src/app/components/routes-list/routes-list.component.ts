import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {FavoritesService} from "../../shared/favorites.service";
import {RouteStopSchedule} from "../../models/rotutes";

@Component({
  selector: 'app-routes-list',
  templateUrl: './routes-list.component.html',
  styleUrls: ['./routes-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RoutesListComponent {
  @Input() urlPrefix: string = ""
  @Input() urlSuffix: string = ""
  @Input() routes: RouteStopSchedule[] = []

  constructor(private favoritesService: FavoritesService) {
  }

  toggleFavorite(route: RouteStopSchedule): void {
    this.favoritesService.toggle("routes", route)
  }

  isFavorite(route: RouteStopSchedule): boolean {
    return this.favoritesService.contain("routes", route)
  }
}
