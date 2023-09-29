import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SidenavComponent } from "./sidenav/sidenav.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { MaterialModule } from "./material.module";
import { RouterModule } from "@angular/router";

@NgModule({
  declarations: [SidenavComponent, NavbarComponent],
  imports: [CommonModule, MaterialModule, RouterModule],
  exports: [SidenavComponent, NavbarComponent],
})
export class SharedModule {}
