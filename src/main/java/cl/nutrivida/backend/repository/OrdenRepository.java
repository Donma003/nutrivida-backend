package cl.nutrivida.backend.repository;

import cl.nutrivida.backend.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByClienteCorreo(String clienteCorreo);
}