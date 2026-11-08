package refactorizado;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

record Paciente(String nombre, String email) {}
record Profesional(String nombre, String especialidad) {}

enum TipoServicio {
    BASICO(50.0), INTERMEDIO(80.0), PREMIUM(120.0);
    private final double tarifa;
    TipoServicio(double tarifa) { this.tarifa = tarifa; }
    public double getTarifa() { return tarifa; }
}

class Turno {
    private final String id;
    private final Paciente paciente;
    private final Profesional profesional;
    private final LocalDateTime fechaHora;
    private final TipoServicio tipoServicio;

    public Turno(String id, Paciente paciente, Profesional profesional, LocalDateTime fechaHora, TipoServicio tipoServicio) {
        this.id = id;
        this.paciente = paciente;
        this.profesional = profesional;
        this.fechaHora = fechaHora;
        this.tipoServicio = tipoServicio;
    }
    public String getId() { return id; }
    public Paciente getPaciente() { return paciente; }
    public Profesional getProfesional() { return profesional; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public TipoServicio getTipoServicio() { return tipoServicio; }
}

interface TurnoValidator { boolean esValido(Turno turno); }
interface NotificadorService { void enviarConfirmacion(Turno turno); }

public class TurnoManagerRefactorizado {
    // Requisito: Simular base de datos estructurada
    private final List<Turno> listaTurnos = new ArrayList<>();
    private final TurnoValidator validator;
    private final NotificadorService notificador;

    public TurnoManagerRefactorizado(TurnoValidator validator, NotificadorService notificador) {
        this.validator = validator;
        this.notificador = notificador;
    }

    // Operación 1: Procesar
    public boolean procesarTurno(Turno turno) {
        if (!validator.esValido(turno)) return false; // Guard Clause
        listaTurnos.add(turno);
        notificador.enviarConfirmacion(turno);
        return true;
    }

    // Operación 2: Cancelar
    public boolean cancelarTurno(String id) {
        return listaTurnos.removeIf(t -> t.getId().equals(id));
    }

    // Operación 3: Buscar
    public Optional<Turno> buscarTurno(String clienteNombre) {
        return listaTurnos.stream()
                .filter(t -> t.getPaciente().nombre().equalsIgnoreCase(clienteNombre))
                .findFirst();
    }

    // Operación 4: Reporte
    public List<Turno> generarReporte() {
        return Collections.unmodifiableList(listaTurnos);
    }
}