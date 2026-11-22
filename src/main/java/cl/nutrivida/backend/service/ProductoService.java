package cl.nutrivida.backend.service;

import cl.nutrivida.backend.model.Producto;
import cl.nutrivida.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public List<Producto> listarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public Producto crear(Producto producto) {
        if (productoRepository.existsByCodigo(producto.getCodigo())) {
            throw new RuntimeException("El código del producto ya existe");
        }
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto existente = obtenerPorId(id);
        existente.setCodigo(datos.getCodigo());
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        existente.setStockCritico(datos.getStockCritico());
        existente.setCategoria(datos.getCategoria());
        existente.setImagen(datos.getImagen());
        return productoRepository.save(existente);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}