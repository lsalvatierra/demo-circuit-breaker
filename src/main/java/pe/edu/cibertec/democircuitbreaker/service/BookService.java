package pe.edu.cibertec.democircuitbreaker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    // El atributo 'name' DEBE coincidir con el definido en application.properties
    @CircuitBreaker(name = "bookService", fallbackMethod = "fallbackGetBook")
    public String getBook(String id, boolean fail) {
        if (fail) {
            // Simulamos que la base de datos o el servicio externo falló
            throw new RuntimeException("ERROR_INTERNO: El servidor tardó demasiado en responder");
        }
        return "ÉXITO: Libro " + id + " entregado correctamente.";
    }

    // Este es el método de respaldo.
    // Los parámetros deben ser EXACTAMENTE los mismos que el original, añadiendo el Throwable al final.
    public String fallbackGetBook(String id, boolean fail, Throwable t) {
        return "FALLBACK - Servicio degradado temporalmente. (Tipo de error bloqueado: " + t.getClass().getSimpleName() + ")";
    }
}
