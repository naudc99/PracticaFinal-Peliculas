// detalle-pelicula.component.ts

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PeliculasService } from '../../services/peliculas/peliculas.service';
import { Pelicula } from '../../services/peliculas/peliculas';

@Component({
  selector: 'app-detalle-pelicula',
  templateUrl: './detalle-pelicula.component.html',
  styleUrls: ['./detalle-pelicula.component.css']
})
export class DetallePeliculaComponent implements OnInit {

  pelicula: Pelicula = {} as Pelicula;

  constructor(
    private route: ActivatedRoute,
    private peliculaService: PeliculasService
  ) { }

  ngOnInit(): void {
    this.getPelicula();
  }

  getPelicula(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id !== null && id !== undefined) {
      const parsedId = +id;

      if (!isNaN(parsedId)) {
        this.peliculaService.getPeliculaById(parsedId)
          .subscribe(pelicula => this.pelicula = pelicula);
      } else {
        console.error('ID de película no válido');
      }
    } else {
      console.error('ID de película no proporcionado');
    }
  }
}
