import { AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';
import { Observable } from 'rxjs';

/*export function uniqueNameValidator(value: string): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const inputValue = control.value as string;

    if (inputValue === value) {
      return { notEqual: true }; // La validación falla
    }

    return null; // La validación pasa
  };
}*/

export function isTenAsync(
  control: AbstractControl
): Observable<ValidationErrors | null> {
  const v: number = control.value;
  if (v !== 10) {
    // Emit an object with a validation error.
    return of({ notTen: true, requiredValue: 10 });
  }
  // Emit null, to indicate no error occurred.
  return of(null);
}
