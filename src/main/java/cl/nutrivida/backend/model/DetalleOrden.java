package cl.nutrivida.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_ordenes")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productoNombre;
    private Integer cantidad;
    private Integer precioUnitario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;
}