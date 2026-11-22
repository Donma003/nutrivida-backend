package cl.nutrivida.backend.repository;

import cl.nutrivida.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(String categoria);
    boolean existsByCodigo(String codigo);
}