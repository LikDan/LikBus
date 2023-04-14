import {Component, Input} from '@angular/core';

@Component({
  selector: 'gmap',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent {
  @Input() stopLat!: number
  @Input() stopLng!: number
  @Input() startLat!: number
  @Input() startLng!: number
  @Input() endLat!: number
  @Input() endLng!: number

  @Input() stopLabel: string = ''
  @Input() startLabel: string = ''
  @Input() endLabel: string = ''

  get stopPoint(): google.maps.LatLngLiteral | undefined {
    if (!this.stopLat || !this.stopLng) return undefined
    return {
      lat: this.stopLat,
      lng: this.stopLng,
    }
  }

  get busStartPoint(): google.maps.LatLngLiteral | undefined  {
    if (!this.startLat || !this.startLng) return undefined
    return {
      lat: this.startLat,
      lng: this.startLng,
    }
  }

  get busEndPoint(): google.maps.LatLngLiteral | undefined  {
    if (!this.endLat || !this.endLng) return undefined
    return {
      lat: this.endLat,
      lng: this.endLng,
    }
  }

  get center(): google.maps.LatLngLiteral | undefined {
    if (!!this.stopPoint) return this.stopPoint
    if (!!this.busStartPoint) return this.busStartPoint
    if (!!this.busEndPoint) return this.busEndPoint

    return undefined
  }
}

