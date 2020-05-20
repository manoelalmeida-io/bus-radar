import { Component, OnInit } from "@angular/core";
import auth from "../../config/auth.json";

@Component({
    selector: "Home",
    templateUrl: "./home.component.html"
})
export class HomeComponent implements OnInit {

    mapboxKey = auth["mapbox-public-key"];

    constructor() {
        // Use the component constructor to inject providers.
    }

    ngOnInit(): void {
        // Init your component properties here.
    }

    onMapReady(event) {

    }
}
