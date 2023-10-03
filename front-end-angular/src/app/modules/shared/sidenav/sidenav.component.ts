import { Component, OnInit } from "@angular/core";
import { MediaMatcher } from "@angular/cdk/layout";

@Component({
  selector: "app-sidenav",
  templateUrl: "./sidenav.component.html",
  styleUrls: ["./sidenav.component.css"],
})
export class SidenavComponent implements OnInit {
  mobileQuery: MediaQueryList;

  menuNav = [
    {
      name: "Home",
      route: "home",
      icon: "home",
    },
    {
      name: "Categoría",
      route: "category",
      icon: "category",
    },
    {
      name: "Productos",
      route: "product",
      icon: "shopping_cart",
    },
  ];
  /**MEDIA Query */
  constructor(media: MediaMatcher) {
    this.mobileQuery = media.matchMedia("max-witdh:600px");
  }

  ngOnInit(): void {}
}
