package cl.nutrivida.backend.service;

import cl.nutrivida.backend.model.Categoria;
import cl.nutrivida.backend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria crear(Categoria categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria datos) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        return categoriaRepository.save(existente);
    }

    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}