package cl.nutrivida.backend.service;

import cl.nutrivida.backend.model.Usuario;
import cl.nutrivida.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario crear(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario datos) {
        Usuario existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setCorreo(datos.getCorreo());
        existente.setRol(datos.getRol());
        return usuarioRepository.save(existente);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}