package es.fct.model;

import lombok.*;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Peliculas")
public class Peliculas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPelicula;
    
    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String sinopsis;

    @ManyToOne
    @JoinColumn(name = "idGenero", nullable = false)
    private Generos genero;

    @ManyToOne
    @JoinColumn(name = "idDirector", nullable = false)
    private Directores director;

    @Column
    private Integer anioEstreno;

    @Column(length = 255)
    private String actoresActuantes;

    // Relación con Actores
    @ManyToMany
    @JoinTable(
        name = "PeliculasActores",
        joinColumns = @JoinColumn(name = "idPelicula"),
        inverseJoinColumns = @JoinColumn(name = "idActor")
    )
    private List<Actores> actores;
}

