import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.staging';

@Injectable({
  providedIn: 'root',
})
export class ServiceService {
  constructor(private httpClient: HttpClient) {}

  getApi(url: string) {
    const urlGet = `${environment.apiUrl}${url}`;
    return this.httpClient.get(urlGet);
  }

  postApi(url: string, data: any) {
    const urlGet = `${environment.apiUrl}${url}`;
    return this.httpClient.post(urlGet, data);
  }

  putApi(url: string, data: any) {
    const urlGet = `${environment.apiUrl}${url}`;
    return this.httpClient.put(urlGet, data);
  }

  deleteApi(url: string) {
    const urlGet = `${environment.apiUrl}${url}`;
    return this.httpClient.delete(urlGet);
  }
}
