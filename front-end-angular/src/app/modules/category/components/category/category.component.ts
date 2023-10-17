import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { ServiceService } from '../../../shared/services/service.service';
import { CategoriaModel } from 'src/model/categoriaModel';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { NewCategoryComponent } from '../new-category/new-category.component';
import { ConfirmComponent } from 'src/app/modules/shared/confirm/confirm.component';
import {
  MatSnackBar,
  MatSnackBarRef,
  SimpleSnackBar,
} from '@angular/material/snack-bar';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  categoryListUrl = '/categoria/list';
  categoryUrl = '/categoria/';

  private service = inject(ServiceService);
  private snackBar = inject(MatSnackBar);
  private dialog = inject(MatDialog);

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  ngOnInit(): void {
    this.getCategoria();
  }

  dataSource = new MatTableDataSource<CategoriaModel>([]);
  displayedColumns: string[] = ['id', 'name', 'description', 'actions'];

  getCategoria() {
    this.service.getApi(this.categoryListUrl).subscribe(
      (response: any) => {
        this.dataSource.data = response;
        this.dataSource.paginator = this.paginator;
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

  deleteCategoria(id: number) {
    const dialogRef = this.dialog.open(ConfirmComponent, {
      data: {
        id: id,
      },
      width: '450px',
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        this.openSnackBar('Categoría Eliminada', 'Exitosa');
        this.getCategoria();
      } else {
        this.openSnackBar('Se produjo un error al eliminar categoria', 'Error');
      }
    });
  }

  buscarCategoria(termino: string) {
    if (termino.length === 0) {
      return this.getCategoria();
    } else {
      this.service.getApi(`${this.categoryUrl}${termino}`).subscribe(
        (response: any) => {
          this.dataSource.data = [response];
          this.dataSource.paginator = this.paginator;
        },
        (error: any) => {
          console.log(error);
        }
      );
    }
  }
  //**  MODALES */
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
