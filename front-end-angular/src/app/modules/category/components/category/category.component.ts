import { Component, OnInit, inject } from '@angular/core';
import { ServiceService } from '../../../shared/services/service.service';
import { CategoriaModel } from 'src/model/categoriaModel';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { NewCategoryComponent } from '../new-category/new-category.component';
import {
  MatSnackBar,
  MatSnackBarRef,
  SimpleSnackBar,
} from '@angular/material/snack-bar';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  categoryUrl = '/categoria/list';

  private service = inject(ServiceService);
  private snackBar = inject(MatSnackBar);
  private dialog = inject(MatDialog);

  ngOnInit(): void {
    this.getCategoria();
  }

  dataSource: MatTableDataSource<CategoriaModel> = new MatTableDataSource();
  displayedColumns: string[] = ['id', 'name', 'description', 'actions'];

  getCategoria() {
    this.service.getApi(this.categoryUrl).subscribe(
      (response: any) => {
        this.dataSource.data = response;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  editCategoria(id: number, name: string, description: string) {
    const dialogRef = this.dialog.open(NewCategoryComponent, {
      data: {
        id: id,
        name: name,
        description: description,
      },
      width: '450px',
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.openSnackBar('Categoría Actualizada', 'Exitosa');
        this.getCategoria();
      } else {
        this.openSnackBar(
          'Se produjo un error al actualizar categoria',
          'Error'
        );
      }
    });
  }

  openModalCategoria(): void {
    const dialogRef = this.dialog.open(NewCategoryComponent, {
      width: '450px',
      /**Asi se puede mandar datos predefinidos */
      //data: {name: this.name, animal: this.description},
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.openSnackBar('Categoría Agregada', 'Exitosa');
        this.getCategoria();
      } else if (result === false) {
        this.openSnackBar('Se produjo un error al guardar categoria', 'Error');
      }
    });
  }

  openSnackBar(msg: string, action: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(msg, action, {
      duration: 2000,
    });
  }
}
