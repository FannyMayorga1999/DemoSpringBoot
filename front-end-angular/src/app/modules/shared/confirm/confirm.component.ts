import { Component, OnInit, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ServiceService } from '../services/service.service';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css'],
})
export class ConfirmComponent implements OnInit {
  categoryUrl = '/categoria';

  private dialogRef = inject(MatDialogRef);
  private data = inject(MAT_DIALOG_DATA);
  private service = inject(ServiceService);

  ngOnInit(): void {}

  onDelete() {
    if (this.data != null) {
      this.service.deleteApi(`${this.categoryUrl}/${this.data.id}`).subscribe(
        (response: any) => {
          this.dialogRef.close(true);
        },
        (error: any) => {
          this.dialogRef.close(false);
        }
      );
    } else {
      this.dialogRef.close(false);
    }
  }

  onCancel() {
    this.dialogRef.close();
  }
}
