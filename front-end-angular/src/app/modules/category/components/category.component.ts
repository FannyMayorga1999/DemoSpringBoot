import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../shared/services/service.service';
import { CategoriaModel } from 'src/model/categoriaModel';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  categoryUrl = '/categoria/list';

  constructor(private service: ServiceService) {}

  ngOnInit(): void {
    this.getProducts();
  }

  dataSource: MatTableDataSource<CategoriaModel> = new MatTableDataSource();
  displayedColumns: string[] = ['id', 'name', 'description', 'actions'];

  getProducts() {
    this.service.get(this.categoryUrl).subscribe(
      (response: any) => {
        this.dataSource.data = response;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
