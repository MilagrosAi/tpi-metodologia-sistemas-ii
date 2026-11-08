# Trabajo Práctico Integrador (TPI) - Metodología de Sistemas II

**Alumna:** Milagros Abril Airalde  
**Materia:** Metodología de Sistemas II  
**Caso de Estudio:** GestorTurnos

## Resumen del Proyecto
Este repositorio contiene la resolución del Trabajo Práctico Integrador. El objetivo principal fue tomar un sistema base (`GestorTurnosOriginal`) que presentaba graves deficiencias de diseño (alta deuda técnica, baja cohesión y alto acoplamiento) y transformarlo en una arquitectura limpia, extensible y mantenible.

## Problemas Detectados (Deuda Técnica)
El código legacy presentaba los siguientes *code smells*:
- **Long Method:** Toda la lógica centralizada en un solo método procedimental.
- **Primitive Obsession:** Uso de `String` e `int` en lugar de objetos de dominio.
- **Arrow Anti-pattern:** Múltiples condicionales anidados (`if-else`).
- **SRP Violation:** La clase validaba, calculaba tarifas y enviaba notificaciones.

## Refactorings Aplicados
Para sanear la deuda técnica, se aplicaron principios **SOLID** y técnicas de **Clean Code**:
1. **Introduce Parameter Object:** Creación de los Records `Paciente` y `Profesional`.
2. **Extract Class / Method:** Separación de responsabilidades en `TurnoValidator`, `NotificadorService` y `TurnoRepository`.
3. **Guard Clauses:** Eliminación del anidamiento condicional en la validación temprana.
4. **Dependency Inversion (DIP):** El servicio principal ahora depende de interfaces, permitiendo inyectar nuevas implementaciones sin modificar el código base.

## Checklist de Code Review (Criterios de Aceptación Técnica)
Antes de integrar el código final, se verificó el cumplimiento de los siguientes TAC:
- [x] **Legibilidad:** Estructura autodocumentada con nombres semánticos.
- [x] **Complejidad Ciclomática:** Reducida mediante Guard Clauses.
- [x] **Encapsulamiento:** Uso de inmutabilidad (Records).
- [x] **Cohesión y Acoplamiento:** Clases con responsabilidad única y dependencias invertidas.

---
*Este repositorio fue construido demostrando el uso de ramas, commits atómicos y etiquetado (tags) para evidenciar las distintas etapas del refactoring.*