import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Entry } from './entry';

@Injectable({
  providedIn: 'root'
})

export class EntryService {

  private baseURL = "http://localhost:8081/app/api/v1";

  constructor(private httpClient: HttpClient) { }

  saveEntry(entry: Entry) : Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/entry`, entry);
  }

  getTop10EntryList(): Observable<Entry[]>{
      return this.httpClient.get<Entry[]>(`${this.baseURL}/entries/top-scores`);
  }

}
