package es.fct.model;

import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPelicula;
    
    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String sinopsis;
    
    @Column(nullable = false, length = 255)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "idGenero", nullable = false)
    @JsonIgnore
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "idDirector", nullable = false)
    @JsonIgnore
    private Director director;

    @Column
    private Integer anioEstreno;

    @ManyToMany
    @JoinTable(
        name = "PeliculasActores",
        joinColumns = @JoinColumn(name = "idPelicula"),
        inverseJoinColumns = @JoinColumn(name = "idActor")
    )
    @JsonIgnore
    private List<Actor> actores;
}

