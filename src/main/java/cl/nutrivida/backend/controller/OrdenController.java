package cl.nutrivida.backend.controller;

import cl.nutrivida.backend.model.Orden;
import cl.nutrivida.backend.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @GetMapping
    public List<Orden> listarTodas() {
        return ordenService.listarTodas();
    }

    @GetMapping("/cliente/{correo}")
    public List<Orden> listarPorCliente(@PathVariable String correo) {
        return ordenService.listarPorCliente(correo);
    }

    @PostMapping
    public ResponseEntity<Orden> crear(@RequestBody Orden orden) {
        return ResponseEntity.ok(ordenService.crearOrden(orden));
    }
}