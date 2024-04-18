import { Component } from '@angular/core';
import { Actor } from '../../services/actores/actores';
import { ActoresService } from '../../services/actores/actores.service';

@Component({
  selector: 'app-actores',
  templateUrl: './actores.component.html',
  styleUrl: './actores.component.css'
})
export class ActoresComponent {

  actores: Actor[] = [];

  constructor(private actorService: ActoresService) { }

  ngOnInit(): void {
    this.getActores();
  }

  getActores() {
    this.actorService.getActores().subscribe(
        data => {
            this.actores = data;
        },
        error => {
            console.log('Error al obtener los actores', error);
        }
    );
}
}
