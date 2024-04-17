package es.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PeliculasActores")
public class PeliculasActores {
    @ManyToOne
    @JoinColumn(name = "idPelicula", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "idActor", nullable = false)
    private Actor actor;
}
