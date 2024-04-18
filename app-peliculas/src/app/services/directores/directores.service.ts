import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Director } from './directores';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DirectoresService {

  private apiUrl = 'http://localhost:8090/api/directores';


  constructor(private http: HttpClient) { }

  getDirectores(): Observable<Director[]> {
    return this.http.get<Director[]>(this.apiUrl);
  }
}
