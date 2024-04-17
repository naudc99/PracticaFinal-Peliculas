package es.fct.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Generos")
public class Generos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;
    
    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "genero")
    private List<Peliculas> peliculas;
}

