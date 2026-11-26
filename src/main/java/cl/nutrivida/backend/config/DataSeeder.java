package cl.nutrivida.backend.config;

import cl.nutrivida.backend.model.Producto;
import cl.nutrivida.backend.model.Usuario;
import cl.nutrivida.backend.repository.ProductoRepository;
import cl.nutrivida.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        cargarUsuarios();
        cargarProductos();
    }

    private void cargarUsuarios() {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin NutriVida");
            admin.setCorreo("admin@nutrivida.cl");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRol("ADMIN");

            Usuario cliente = new Usuario();
            cliente.setNombre("Camila González");
            cliente.setCorreo("cliente@nutrivida.cl");
            cliente.setPassword(passwordEncoder.encode("123456"));
            cliente.setRol("CLIENTE");

            usuarioRepository.saveAll(List.of(admin, cliente));
            System.out.println("✅ Usuarios iniciales cargados en PostgreSQL.");
        }
    }

    private void cargarProductos() {
        if (productoRepository.count() == 0) {
            Producto p1 = crearProducto(
                "CN001", "Primera consulta nutricional",
                "Evaluación inicial: anamnesis, antropometría completa y diseño del primer plan alimenticio.",
                35000, 50, 5, "Consulta",
                "https://images.unsplash.com/photo-1576091160399-112ba8d25d1d?w=500&auto=format&fit=crop&q=60"
            );

            Producto p2 = crearProducto(
                "CN002", "Control nutricional (seguimiento)",
                "Seguimiento mensual: medición de indicadores y ajuste del plan vigente.",
                25000, 40, 5, "Consulta",
                "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?w=500&auto=format&fit=crop&q=60"
            );

            Producto p3 = crearProducto(
                "PL001", "Plan pérdida de peso (1 mes)",
                "Incluye primera consulta + 1 control quincenal + plan alimenticio personalizado + seguimiento.",
                65000, 20, 3, "Plan especializado",
                "https://images.unsplash.com/photo-1498837167922-ddd27525d352?w=500&auto=format&fit=crop&q=60"
            );

            Producto p4 = crearProducto(
                "PL003", "Plan nutrición deportiva (1 mes)",
                "Para deportistas y personas con actividad física frecuente. Cálculo de requerimientos energéticos.",
                70000, 12, 2, "Plan especializado",
                "https://images.unsplash.com/photo-1517838277536-f5f99be501cd?w=500&auto=format&fit=crop&q=60"
            );

            Producto p5 = crearProducto(
                "EV001", "Antropometría completa",
                "Peso, talla, IMC, circunferencia de cintura, cadera y % de grasa corporal.",
                18000, 50, 10, "Evaluación",
                "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=500&auto=format&fit=crop&q=60"
            );

            Producto p6 = crearProducto(
                "TG001", "Taller de alimentación saludable",
                "Máx. 10 personas. Conceptos básicos de alimentación equilibrada y lectura de etiquetas.",
                15000, 8, 2, "Taller grupal",
                "https://images.unsplash.com/photo-1556910103-1c02745aae4d?w=500&auto=format&fit=crop&q=60"
            );

            productoRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));
            System.out.println("✅ Catálogo inicial cargado en PostgreSQL.");
        }
    }

    private Producto crearProducto(String codigo, String nombre, String descripcion,
                                   Integer precio, Integer stock, Integer stockCritico,
                                   String categoria, String imagen) {
        Producto p = new Producto();
        p.setCodigo(codigo);
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setStockCritico(stockCritico);
        p.setCategoria(categoria);
        p.setImagen(imagen);
        return p;
    }
}