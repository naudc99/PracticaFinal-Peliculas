import { Component } from '@angular/core';
import { Director } from '../../services/directores/directores';
import { DirectoresService } from '../../services/directores/directores.service';

@Component({
  selector: 'app-directores',
  templateUrl: './directores.component.html',
  styleUrl: './directores.component.css'
})
export class DirectoresComponent {
  directores: Director[] = [];

  constructor(private directorService: DirectoresService) { }

  ngOnInit(): void {
    this.getDirectores();
  }

  getDirectores() {
    this.directorService.getDirectores().subscribe(
        data => {
            this.directores = data;
        },
        error => {
            console.log('Error al obtener los directores', error);
        }
    );
}
}
