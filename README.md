# Tarea2 G21
Integrantes:
Javiera Antonia Diaz Grandon 
Tomas Ignacio Pizarro Abarca
Pablo Sebastian Bascuñan Espina

Este proyecto consiste en la simulacion de un sistema corporativo para la gestion de reuniones empresariales desarrollado en java, 
el sistema permite planificar reuniones presenciales y virtuales, controlar las listas de invitados, registrar la asistencia en tiempo real 
(identificando asistentes a tiempo y retrasos), calcular estadisticas de participacion y la exportacion de un informe detallado en formato .txt.

El codigo se organiza bajo el paquete "Tarea2" e incluye las siguientes funcionalidades:
sistema de participantes: implementacion de clases abstractas para "Persona" con sus respectivos miembros especificos "Empleado" e "InvitadoExterno".
gestion de reuniones: uso de una jerarquia de reuniones con clases especializadas para "ReunionVirtual" y "ReunionPresencial".
logica de negocio: existen clases como "Invitacion", "Asistencia" y "Retraso" que controlan el flujo de los participantes y el calculo de ausencias.
categorizacion y reportabilidad: uso de un enum "tipoReunion" para clasificar las reuniones y ejecucion de informes automaticos.

Para ejecutar el proyecto:
1. Clonar el repositorio.
2. Abrir el proyecto en un IDE (intelliJ IDEA recomendado).
3. Configurar el SDK (java 17 o superior).
4. Ejecutar la clase "mardedudas" ubicada en "src/main/java/Tarea2/Mardedudas.java".

El diagrama se completo mediante las herramientas de git y la justificacion del diseño y modificaciones son :
1. Herencia de Persona: se identifico que Empleado e InvitadoExterno compartian atributos base (nombre, apellido, correo), se creo la clase abstracta Persona para evitar la duplicacion de codigo y permitir un manejo polimorfico de los participantes en las listas de invitaciones.
2. Uso de un Enum (tipoReunion): se implemento para restringir las categorias de las reuniones a opciones validas y estandarizadas (TECNICA, MARKETING, ADMINISTRATIVA, DIRECTIVA), evitando errores de tipeo en la logica del negocio.
3. Clase Invitacion y Metodo obtenerAusencias(): se creo el objeto Invitacion como puente formal entre Reunion y Empleado. Esto permitio programar la logica de ausencias comparando los invitados agendados contra los registros reales de asistencia.

```mermaid
classDiagram
    %% Relaciones de Herencia
    Persona <|-- Empleado
    Persona <|-- InvitadoExterno
    Reunion <|-- ReunionVirtual
    Reunion <|-- ReunionPresencial
    Asistencia <|-- Retraso

    %% Relaciones de Interfaz (Realización)
    Invitable <|.. Empleado
    Invitable <|.. Departamento
    Invitable <|.. Invitacion

    %% Relaciones de Asociación y Agregación
    Reunion --> tipoReunion : usa
    Reunion "1" *-- "*" Invitacion : contiene
    Reunion "1" *-- "*" Asistencia : registra
    Reunion "1" *-- "1" Nota : organizada por
    Invitacion --> Empleado : asignada a
    Asistencia --> Empleado : registra a
    Departamento "1" *-- "*" Empleado : contiene

    %% Definición de Clases
    class Persona {
        <<abstract>>
        - String nombre
        - String apellidos
        - String correo
    }

    class Empleado {
        - String id
    }

    class InvitadoExterno {
        - String empresa
    }

    class Departamento {
        - String nombre
        + obtenerCantidadEmpleados() int
    }

    class Invitable {
        <<interface>>
        + invitar() void
    }

    class Nota {
        - String contenido
    }

    class Reunion {
        <<abstract>>
        - Date fecha
        - Instant horaPrevista
        - Duration duracionPrevista
        - Instant horaInicio
        - Instant horaFin
        + obtenerAsistencias() List
        + obtenerRetrasos() List
        + obtenerAusencias() List
        + obtenerTotalAsistencia() int
        + obtenerPorcentajeAsistencia() float
        + calcularTiempoReal() float
        + iniciar() void
        + finalizar() void
        + generarInformeTxt(String) void
    }

    class ReunionVirtual {
        - String enlace
    }

    class ReunionPresencial {
        - String sala
    }

    class Invitacion {
        - Instant hora
        + getEmpleado() Empleado
    }

    class Asistencia {
        - Instant horaLlegada
    }

    class Retraso {
        - String motivo
    }

    class tipoReunion {
        <<enumeration>>
        TECNICA
        MARKETING
        ADMINISTRATIVA
        DIRECTIVA
    }
