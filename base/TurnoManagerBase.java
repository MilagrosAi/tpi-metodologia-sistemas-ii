package base;
import java.util.ArrayList;
import java.util.List;

public class TurnoManagerBase {
    // Requisito: Persistir o simular datos (Punto 4 del PDF)
    private List<String> baseDeDatos = new ArrayList<>();

    // Operación 1: Procesar
    public boolean procesarTurno(String clienteNombre, String clienteEmail, String medicoNombre, String especialidad, String fecha, String hora, int tipoServicio) {
        if (clienteNombre != null && !clienteNombre.equals("")) {
            if (clienteEmail != null && clienteEmail.contains("@")) {
                if (medicoNombre != null && !medicoNombre.equals("")) {
                    double precioBase = 0;
                    if (tipoServicio == 1) precioBase = 50.0;
                    else if (tipoServicio == 2) precioBase = 80.0;
                    else if (tipoServicio == 3) precioBase = 120.0;
                    
                    String registro = "TURNO:" + clienteNombre + "|" + medicoNombre + "|" + fecha + " " + hora + "|$" + precioBase;
                    baseDeDatos.add(registro);
                    System.out.println("Guardando en BD: " + registro);
                    return true;
                }
            }
        }
        return false;
    }

    // Operación 2: Cancelar
    public boolean cancelarTurno(String id) {
        System.out.println("Turno cancelado en la lista.");
        return true;
    }

    // Operación 3: Buscar
    public String buscarTurno(String clienteNombre) {
        return "Turno encontrado para " + clienteNombre;
    }

    // Operación 4: Reporte
    public void generarReporte() {
        System.out.println("Total de turnos cargados: " + baseDeDatos.size());
    }
}