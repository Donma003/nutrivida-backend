package cl.nutrivida.backend.controller;

import cl.nutrivida.backend.config.JwtUtil;
import cl.nutrivida.backend.model.Usuario;
import cl.nutrivida.backend.repository.UsuarioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            return ResponseEntity.badRequest().body(Map.of("error", "El correo ya está registrado"));
        }
        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            usuario.setRol("CLIENTE");
        } else {
            usuario.setRol(usuario.getRol().toUpperCase());
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario guardado = usuarioRepository.save(usuario);

        String token = jwtUtil.generarToken(guardado.getCorreo(), guardado.getRol());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", Map.of(
                        "id", guardado.getId(),
                        "nombre", guardado.getNombre(),
                        "correo", guardado.getCorreo(),
                        "rol", guardado.getRol()
                )
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        var usuarioOpt = usuarioRepository.findByCorreo(request.getCorreo());
        if (usuarioOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), usuarioOpt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }

        Usuario usuario = usuarioOpt.get();
        String token = jwtUtil.generarToken(usuario.getCorreo(), usuario.getRol());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", Map.of(
                        "id", usuario.getId(),
                        "nombre", usuario.getNombre(),
                        "correo", usuario.getCorreo(),
                        "rol", usuario.getRol()
                )
        ));
    }

    @Data
    public static class LoginRequest {
        private String correo;
        private String password;
    }
}