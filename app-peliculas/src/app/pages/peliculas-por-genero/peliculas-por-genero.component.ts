// peliculas-por-genero.component.ts

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PeliculasService } from '../../services/peliculas/peliculas.service';
import { Pelicula } from '../../services/peliculas/peliculas';

@Component({
  selector: 'app-peliculas-por-genero',
  templateUrl: './peliculas-por-genero.component.html',
  styleUrls: ['./peliculas-por-genero.component.css']
})
export class PeliculasPorGeneroComponent implements OnInit {
  peliculas: Pelicula[] = [];

  constructor(
    private route: ActivatedRoute,
    private peliculasService: PeliculasService
  ) { }

  ngOnInit(): void {
    this.getPeliculasPorGenero();
  }

  getPeliculasPorGenero(): void {
    const id = this.route.snapshot.paramMap.get('id');
    console.log('ID del género:', id);
    
    if (id !== null && id !== undefined && !isNaN(+id)) {
      const parsedId = +id;
      this.peliculasService.getPeliculasPorGenero(parsedId)
        .subscribe(peliculas => this.peliculas = peliculas);
    } else {
      console.error('ID de género no válido');
    }
  }
  
  
  
}
