import {Component, Input} from '@angular/core';

@Component({
  selector: 'gmap',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent {
  @Input() lat: number = 0
  @Input() lng: number = 0

  @Input() label: string = 'Marker label main'

  get point(): google.maps.LatLngLiteral {
    return {
      lat: this.lat,
      lng: this.lng,
    }
  }
}

