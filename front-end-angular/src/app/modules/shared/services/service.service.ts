import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment.staging";

@Injectable({
  providedIn: "root",
})
export class ServiceService {
  constructor(private httpClient: HttpClient) {}

  get(url: string) {
    const urlGet = `${environment.apiUrl}${url}`;
    console.log(urlGet);
    return this.httpClient.get(urlGet);
  }
}
