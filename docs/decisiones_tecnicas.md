# Registro de Decisiones Técnicas (ADR)
## Proyecto: GestorTurnos

### 1. Cambio a Objetos de Dominio
- **Contexto:** El sistema original utilizaba 7 parámetros primitivos (Strings e int) en la firma del método principal (Primitive Obsession).
- **Decisión:** Se aplicó el refactoring *Introduce Parameter Object*, creando los records `Paciente`, `Profesional` y el enum `TipoServicio`.
- **Consecuencias Positivas:** Firma del método reducida a 1 parámetro (`Turno`), mejorando el encapsulamiento y la validación semántica.

### 2. Desacoplamiento de Responsabilidades (SRP / Move Method)
- **Contexto:** La clase monolítica validaba datos, calculaba precios y enviaba notificaciones.
- **Decisión:** Se extrajeron estas lógicas a interfaces inyectadas (`TurnoValidator`, `NotificadorService`). El cálculo del costo se delegó al Enum `TipoServicio` aplicando *Move Method*.
- **Consecuencias Positivas:** El orquestador ya no debe modificarse si cambian las reglas de validación o los canales de envío de mensajes.