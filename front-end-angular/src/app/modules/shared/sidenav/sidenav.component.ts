import { Component, OnInit } from "@angular/core";
import { MediaMatcher } from "@angular/cdk/layout";

@Component({
  selector: "app-sidenav",
  templateUrl: "./sidenav.component.html",
  styleUrls: ["./sidenav.component.css"],
})
export class SidenavComponent implements OnInit {
  mobileQuery: MediaQueryList;

  /**MEDIA Query */
  constructor(media: MediaMatcher) {
    this.mobileQuery = media.matchMedia("max-witdh:600px");
  }

  ngOnInit(): void {}
}
