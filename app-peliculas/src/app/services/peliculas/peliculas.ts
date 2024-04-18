// pelicula.model.ts
export interface Pelicula {
  idPelicula: number;
  titulo: string;
  sinopsis: string;
  imagen: string;
  genero: {
    idGenero: number;
    nombre: string;
  };
  director: {
    idDirector: number;
    nombre: string;
    apellido: string;
  };
  anioEstreno: number;
  actores: {
    idActor: number;
    nombre: string;
    apellido: string;
  }[];
}
