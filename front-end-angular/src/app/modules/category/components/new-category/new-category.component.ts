import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ServiceService } from 'src/app/modules/shared/services/service.service';

@Component({
  selector: 'app-new-category',
  templateUrl: './new-category.component.html',
  styleUrls: ['./new-category.component.css'],
})
export class NewCategoryComponent implements OnInit {
  categoryUrl = '/categoria';
  btnSaveCategoria = true;
  btnActualizarCategoria = false;

  public categoryForm!: FormGroup;
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef);
  private data = inject(MAT_DIALOG_DATA);
  private service = inject(ServiceService);

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
    });

    if (this.data) {
      this.btnSaveCategoria = false;
      this.btnActualizarCategoria = true;
      this.updateForm(this.data);
    }
  }

  onSave() {
    /*let data = this.categoryForm.value;
     */
    let data = {
      name: this.categoryForm.get('name')?.value,
      description: this.categoryForm.get('description')
        ? this.categoryForm.get('description')?.value
        : '',
    };
    this.service.postApi(this.categoryUrl, data).subscribe(
      (response: any) => {
        this.dialogRef.close(true);
      },
      (error: any) => {
        this.dialogRef.close(false);
      }
    );
  }

  onActualizar() {
    /*let data = this.categoryForm.value;
     */
    let data = {
      name: this.categoryForm.get('name')?.value,
      description: this.categoryForm.get('description')
        ? this.categoryForm.get('description')?.value
        : '',
    };
    this.service.putApi(`${this.categoryUrl}/${this.data.id}`, data).subscribe(
      (response: any) => {
        this.dialogRef.close(true);
      },
      (error: any) => {
        this.dialogRef.close(false);
      }
    );
  }

  onCancel() {
    this.dialogRef.close();
  }

  updateForm(data: any) {
    this.categoryForm = this.fb.group({
      name: [data.name, Validators.required],
      description: [data.description],
    });
  }
}
