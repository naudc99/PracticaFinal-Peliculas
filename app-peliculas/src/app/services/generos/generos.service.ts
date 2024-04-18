
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Genero } from './generos';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {

  private apiUrl = 'http://localhost:8090/api/generos';


  constructor(private http: HttpClient) { }

  getGeneros(): Observable<Genero[]> {
    return this.http.get<Genero[]>(this.apiUrl);
  }
}
