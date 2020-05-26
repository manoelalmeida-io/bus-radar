import { Component, OnInit } from "@angular/core";
import auth from "../../config/auth.json";
import { BusService } from "../core/bus.service";
import { Bus } from "../shared/models/bus";

@Component({
	selector: "Home",
	templateUrl: "./home.component.html"
})
export class HomeComponent implements OnInit {

	mapboxKey = auth["mapbox-public-key"];
	buses: Array<Bus> = [];

	constructor(private service: BusService) {
		// Use the component constructor to inject providers.
	}

	ngOnInit(): void {
		this.service.nearby().subscribe((buses: Array<Bus>) => {
			console.log(this.buses);
			this.buses = buses;
		});
	}

	onMapReady(event) {
		console.log(this.buses);
		event.map.addMarkers(this.buses.map((bus: Bus) => ({
			lat: bus.latitude,
      lng: bus.longitude,
      title: bus.line.name,
      subtitle: bus.line.code
		})));
	}
}
