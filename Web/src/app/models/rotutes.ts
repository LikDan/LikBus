import {Stop} from "./stop";
import {Bus} from "./bus";

export interface RouteStopSchedule {
  stop: Stop
  bus: Bus
  types: RouteStopScheduleType[]
}
export interface RouteStopScheduleType {
  type: string
  times: string[]
}
