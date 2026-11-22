package cl.nutrivida.backend.service;

import cl.nutrivida.backend.model.Orden;
import cl.nutrivida.backend.repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository ordenRepository;

    public List<Orden> listarTodas() {
        return ordenRepository.findAll();
    }

    public List<Orden> listarPorCliente(String correo) {
        return ordenRepository.findByClienteCorreo(correo);
    }

    public Orden crearOrden(Orden orden) {
        if (orden.getNumeroBoleta() == null || orden.getNumeroBoleta().isBlank()) {
            orden.setNumeroBoleta("BOL-" + System.currentTimeMillis());
        }
        if (orden.getDetalles() != null) {
            orden.getDetalles().forEach(detalle -> detalle.setOrden(orden));
        }
        return ordenRepository.save(orden);
    }
}