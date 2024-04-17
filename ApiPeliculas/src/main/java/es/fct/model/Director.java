package es.fct.model;

import lombok.*;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Directores")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDirector;
    
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    // Relación con Peliculas
    @OneToMany(mappedBy = "director")
    private List<Pelicula> peliculas;
}

