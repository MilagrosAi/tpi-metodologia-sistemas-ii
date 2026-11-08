import java.time.LocalDateTime;

// 1. Introduce Parameter Object: Encapsulamos datos relacionados
record Paciente(String nombre, String email) {}
record Profesional(String nombre, String especialidad) {}

enum TipoServicio {
    BASICO(50.0), INTERMEDIO(80.0), PREMIUM(120.0);
    private final double tarifa;
    TipoServicio(double tarifa) { this.tarifa = tarifa; }
    public double getTarifa() { return tarifa; }
}

class Turno {
    private final Paciente paciente;
    private final Profesional profesional;
    private final LocalDateTime fechaHora;
    private final TipoServicio tipoServicio;

    public Turno(Paciente paciente, Profesional profesional, LocalDateTime fechaHora, TipoServicio tipoServicio) {
        this.paciente = paciente;
        this.profesional = profesional;
        this.fechaHora = fechaHora;
        this.tipoServicio = tipoServicio;
    }
    public Paciente getPaciente() { return paciente; }
    public Profesional getProfesional() { return profesional; }
    public TipoServicio getTipoServicio() { return tipoServicio; }
}

// 2. Separación de Responsabilidades (SRP) e Inversión de Dependencias (DIP)
interface TurnoValidator { boolean esValido(Turno turno); }
interface TurnoRepository { void guardar(Turno turno); }
interface NotificadorService { void enviarConfirmacion(Turno turno); }

public class TurnoManagerRefactorizado {
    private final TurnoValidator validator;
    private final NotificadorService notificador;
    private final TurnoRepository repository;

    public TurnoManagerRefactorizado(TurnoValidator validator, NotificadorService notificador, TurnoRepository repository) {
        this.validator = validator;
        this.notificador = notificador;
        this.repository = repository;
    }

    public boolean procesarTurno(Turno turno) {
        // 3. Guard Clause: Validación temprana sin anidamiento (elimina el Anti-patrón Arrow)
        if (!validator.esValido(turno)) {
            return false;
        }
        
        repository.guardar(turno);
        notificador.enviarConfirmacion(turno);
        return true;
    }
}