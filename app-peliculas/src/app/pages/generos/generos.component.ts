// generos.component.ts

import { Component, OnInit } from '@angular/core';
import { GeneroService } from '../../services/generos/generos.service';
import { Genero } from '../../services/generos/generos';
import { Router } from '@angular/router';

@Component({
  selector: 'app-generos',
  templateUrl: './generos.component.html',
  styleUrls: ['./generos.component.css']
})
export class GenerosComponent implements OnInit {

  generos: Genero[] = [];

  constructor(private generoService: GeneroService, private router: Router) { }

  ngOnInit(): void {
    this.getGeneros();
  }

  getGeneros() {
    this.generoService.getGeneros().subscribe(
        data => {
            this.generos = data;
        },
        error => {
            console.log('Error al obtener los géneros', error);
        }
    );
  }

  verPeliculasPorGenero(idGenero: number): void {
    this.router.navigate(['/peliculas/genero/' + idGenero]);

  }
  
  
  
  
}
