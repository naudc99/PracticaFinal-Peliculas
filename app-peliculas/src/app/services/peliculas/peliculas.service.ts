// peliculas.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { Pelicula } from './peliculas';

@Injectable({
  providedIn: 'root'
})
export class PeliculasService {
  private apiUrl = 'http://localhost:8090/api/peliculas';


  constructor(private http: HttpClient) { }

  getPeliculas(): Observable<Pelicula[]> {
    return this.http.get<Pelicula[]>(this.apiUrl);
  }

  getPeliculaById(id: number): Observable<Pelicula> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Pelicula>(url);
  }

  getPeliculasPorGenero(idGenero: number): Observable<Pelicula[]> {
    const url = `${this.apiUrl}/genero/${idGenero}`;
    return this.http.get<Pelicula[]>(url)
      .pipe(
        catchError(error => {
          console.error('Error al obtener películas por género', error);
          throw error;
        })
      );
  }
  
}


