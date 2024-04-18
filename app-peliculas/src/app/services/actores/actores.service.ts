import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Actor } from './actores';

@Injectable({
  providedIn: 'root'
})
export class ActoresService {

  private apiUrl = 'http://localhost:8090/api/actores';


  constructor(private http: HttpClient) { }

  getActores(): Observable<Actor[]> {
    return this.http.get<Actor[]>(this.apiUrl);
  }
}
