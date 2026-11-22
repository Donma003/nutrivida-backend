package cl.nutrivida.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    private Integer stock;

    private Integer stockCritico;

    @Column(nullable = false)
    private String categoria;

    private String imagen;
}