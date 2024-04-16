package es.fct.model;

import lombok.*;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column(nullable = false, length = 50)
    private String nombreUsuario;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String contraseña;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false)
    private Roles rol;

    // Relación con Favoritos
    @OneToMany(mappedBy = "usuario")
    private List<Favoritos> favoritos;

    // Relación con Comentarios
    @OneToMany(mappedBy = "usuario")
    private List<Comentarios> comentarios;
}

