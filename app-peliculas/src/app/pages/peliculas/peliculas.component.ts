// peliculas.component.ts
import { Component, OnInit } from '@angular/core';
import { PeliculasService } from '../../services/peliculas/peliculas.service';
import { Pelicula } from '../../services/peliculas/peliculas';
import { Router } from '@angular/router';

@Component({
  selector: 'app-peliculas',
  templateUrl: './peliculas.component.html',
  styleUrls: ['./peliculas.component.css']
})
export class PeliculasComponent implements OnInit {
  peliculas: Pelicula[] = [];


  constructor(private peliculasService: PeliculasService, private router: Router) { }

  ngOnInit(): void {
    this.getPeliculas();
  }

  getPeliculas() {
    this.peliculasService.getPeliculas().subscribe(
        data => {
            this.peliculas = data;
        },
        error => {
            console.log('Error al obtener las películas', error);
        }
    );
  }

  verDetalle(id: number): void {
    this.router.navigate(['/peliculas', id]);
  }
}
