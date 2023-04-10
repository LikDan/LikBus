import {Stop} from "./stop";

export interface Bus {
  id: string
  number: string
}

export interface FullBus {
  bus: Bus
  stops: Stop[]
}
