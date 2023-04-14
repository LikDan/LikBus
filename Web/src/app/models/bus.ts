import {Stop} from "./stop";

export interface Bus {
  id: string
  number: string
  start: string
  end: string
  startLat: number
  startLng: number
  endLat: number
  endLng: number
  type: string
}

export interface FullBus {
  bus: Bus
  stops: Stop[]
}
