import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./components/home/home.component";
import { CategoryComponent } from "../category/components/category.component";

const routerChild: Routes = [
  {
    path: "",
    component: HomeComponent,
  },
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "category",
    component: CategoryComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routerChild)],
  exports: [RouterModule],
})
export class RouterChildModule {}
