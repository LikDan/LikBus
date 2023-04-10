import {Stop} from "./stop";
import {Bus} from "./bus";

export interface RouteStopSchedule {
  stop: Stop
  bus: Bus
  times: string[]
}
