import {Injectable} from '@angular/core';
import {Stop} from "../models/stop";
import {Bus} from "../models/bus";
import {RouteStopSchedule} from "../models/rotutes";


export interface FavoriteItems {
  stops: Stop[] | undefined
  buses: Bus[] | undefined
  routes: RouteStopSchedule[] | undefined
}

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {
  get items(): FavoriteItems {
    return JSON.parse(localStorage.getItem("favorites") ?? "{}") as FavoriteItems
  }

  set items(items: FavoriteItems) {
    localStorage.setItem("favorites", JSON.stringify(items))
  }

  append(type: string, item: any): void {
    const items = this.items
    this.getByName(type, items).push(item)
    this.items = items
  }

  remove(type: string, item: any): void {
    const items = this.items
    const i = this.findItemIndex(type, item)
    if (i < 0) return

    this.getByName(type, items).splice(i, 1)
    this.items = items
  }

  findItemIndex(type: string, item: any | string, items: FavoriteItems = this.items): number {
    return this.getByName(type, items).findIndex(v => JSON.stringify(v) === (typeof item === "string" ? item : JSON.stringify(item)))
  }

  contain(type: string, item: any, items: FavoriteItems = this.items): boolean {
    const itemJSON = JSON.stringify(item)
    const i = this.findItemIndex(type, itemJSON, items)
    return i >= 0
  }

  toggle(type: string, item: any): boolean {
    const contain = this.contain(type, item)
    if (contain) this.remove(type, item)
    else this.append(type, item)
    return contain
  }

  getByName<T = (Bus[] | Stop[] | RouteStopSchedule[])>(type: string, items: FavoriteItems = this.items): T[] {
    if (!items) return []
    // @ts-ignore
    if (!items[type]) items[type] = []

    // @ts-ignore
    return items[type]
  }
}
