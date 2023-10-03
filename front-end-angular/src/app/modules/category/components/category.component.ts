import { Component, OnInit } from "@angular/core";
import { ServiceService } from "../../shared/services/service.service";

@Component({
  selector: "app-category",
  templateUrl: "./category.component.html",
  styleUrls: ["./category.component.css"],
})
export class CategoryComponent implements OnInit {
  categoryUrl = "/categoria/list";

  constructor(private service: ServiceService) {}

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts() {
    this.service.get(this.categoryUrl).subscribe(
      (response: any) => {
        console.log(response);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
