package es.fct.model;

import es.fct.security.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Favoritos")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFavorito;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "idPelicula", nullable = false)
    private Pelicula pelicula;
}
