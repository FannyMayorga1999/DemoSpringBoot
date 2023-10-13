import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SidenavComponent } from "./sidenav/sidenav.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { MaterialModule } from "./material.module";
import { RouterModule } from "@angular/router";
import { ConfirmComponent } from './confirm/confirm.component';

@NgModule({
  declarations: [SidenavComponent, NavbarComponent, ConfirmComponent],
  imports: [CommonModule, MaterialModule, RouterModule],
  exports: [SidenavComponent, NavbarComponent],
})
export class SharedModule {}
