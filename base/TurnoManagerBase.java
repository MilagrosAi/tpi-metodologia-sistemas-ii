package base;

public class TurnoManagerBase {
    public boolean procesarTurno(String clienteNombre, String clienteEmail, String medicoNombre, 
                                 String especialidad, String fecha, String hora, int tipoServicio) {
        // Validacion de cliente
        if (clienteNombre != null && !clienteNombre.equals("")) {
            if (clienteEmail != null && clienteEmail.contains("@")) {
                // Validar medico
                if (medicoNombre != null && !medicoNombre.equals("")) {
                    System.out.println("Validaciones pasadas.");
                    
                    // Calcular precio segun servicio
                    double precioBase = 0;
                    if (tipoServicio == 1) {
                        precioBase = 50.0;
                    } else if (tipoServicio == 2) {
                        precioBase = 80.0;
                    } else if (tipoServicio == 3) {
                        precioBase = 120.0;
                    }
                    
                    // Formatear confirmación
                    String registro = "TURNO:" + clienteNombre + "|" + medicoNombre + "|" + fecha + " " + hora + "|$" + precioBase;
                    System.out.println("Guardando en BD: " + registro);
                    
                    // Enviar Email
                    if (especialidad.equals("Pediatria")) {
                        System.out.println("Enviando mail a " + clienteEmail + ": Turno confirmado con Pediatra " + medicoNombre);
                    } else if (especialidad.equals("Clinica")) {
                        System.out.println("Enviando mail a " + clienteEmail + ": Turno confirmado con Clinico " + medicoNombre);
                    } else {
                        System.out.println("Enviando mail a " + clienteEmail + ": Turno confirmado.");
                    }
                    return true;
                } else {
                    System.out.println("Medico invalido");
                    return false;
                }
            } else {
                System.out.println("Email invalido");
                return false;
            }
        } else {
            System.out.println("Cliente invalido");
            return false;
        }
    }
}