import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http'
import { Entry } from './entry';
import { map, catchError } from 'rxjs/operators';
import { Observable, of, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class EntryService {

  private baseURL = "http://localhost:8082/app/api/v1";

  constructor(private httpClient: HttpClient) { }

  saveEntry(entry: Entry) : Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/entry`, entry);
  }

  getTop10EntryList(): Observable<Entry[]>{
      return this.httpClient.get<Entry[]>(`${this.baseURL}/entries/top-scores`);
  }

}
