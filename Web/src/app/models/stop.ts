import {Bus} from "./bus";

export interface Stop {
  id: string
  title: string
  lat: number
  lng: number
}

export interface FullStop {
  stop: Stop
  buses: Bus[]
}
