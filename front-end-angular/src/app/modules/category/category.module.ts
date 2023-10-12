import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../shared/material.module';
import { CategoryComponent } from './components/category/category.component';
import { NewCategoryComponent } from './components/new-category/new-category.component';

@NgModule({
  declarations: [CategoryComponent, NewCategoryComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MaterialModule],
})
export class CategoryModule {}
