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

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    
    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<Usuarios> usuarios;
}

