import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./pages/dashboard.component";

const routerdDashboard: Routes = [
  {
    path: "dashboard",
    component: DashboardComponent,
    loadChildren: () =>
      import("./router-child.module").then((m) => m.RouterChildModule),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routerdDashboard)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
