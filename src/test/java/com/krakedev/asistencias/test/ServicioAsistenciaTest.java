package com.krakedev.asistencias.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.asistencias.entidades.Asistencia;
import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.entidades.RegistroAsistencia;
import com.krakedev.asistencias.servicios.ServicioAsistencia;
import com.krakedev.asistencias.servicios.ServicioEstudiantes;

public class ServicioAsistenciaTest {

    private ServicioEstudiantes servicioEstudiantes;
    private ServicioAsistencia servicioAsistencia;

    /**
     * Se ejecuta antes de cada prueba.
     * Inicializa los servicios.
     */
    @BeforeEach
    public void setUp() {

        servicioEstudiantes =
                new ServicioEstudiantes();

        servicioAsistencia =
                new ServicioAsistencia(
                        servicioEstudiantes
                );
    }

    /**
     * ESCENARIO:
     * Registrar asistencia de un estudiante existente
     * 
     * Se espera:
     * - Crear correctamente el registro
     * - Asociar estudiante y asistencia
     * - Estado "P"
     */
    @Test
    public void testRegistrarAsistencia() {

        Estudiante estudiante =
                new Estudiante(
                        "123",
                        "Juan",
                        "Perez"
                );

        servicioEstudiantes.agregar(estudiante);

        RegistroAsistencia registro =
                servicioAsistencia
                        .registrarAsistencia("123");

        assertNotNull(registro);

        assertEquals(
                "123",
                registro.getEstudiante().getCedula()
        );

        assertEquals(
                "P",
                registro.getAsistencia().getEstado()
        );
    }

    /**
     * ESCENARIO:
     * Registrar asistencia de un estudiante inexistente
     * 
     * Se espera:
     * - Retornar null
     */
    @Test
    public void testRegistrarAsistenciaNoExistente() {

        RegistroAsistencia registro =
                servicioAsistencia
                        .registrarAsistencia("999");

        assertNull(registro);
    }

    /**
     * ESCENARIO:
     * Consultar asistencias de un estudiante
     * 
     * Se espera:
     * - Retornar todas las asistencias registradas
     */
    @Test
    public void testConsultarAsistencias() {

        Estudiante estudiante =
                new Estudiante(
                        "123",
                        "Juan",
                        "Perez"
                );

        servicioEstudiantes.agregar(estudiante);

        servicioAsistencia
                .registrarAsistencia("123");

        servicioAsistencia
                .registrarAsistencia("123");

        ArrayList<Asistencia> asistencias =
                servicioAsistencia
                        .consultarAsistencia("123");

        assertEquals(2, asistencias.size());
    }

    /**
     * ESCENARIO:
     * Consultar asistencias de un estudiante sin registros
     * 
     * Se espera:
     * - Retornar lista vacía
     */
    @Test
    public void testConsultarAsistenciasVacias() {

        Estudiante estudiante =
                new Estudiante(
                        "123",
                        "Juan",
                        "Perez"
                );

        servicioEstudiantes.agregar(estudiante);

        ArrayList<Asistencia> asistencias =
                servicioAsistencia
                        .consultarAsistencia("123");

        assertEquals(0, asistencias.size());
    }

    /**
     * ESCENARIO:
     * Consultar asistencias de una cédula inexistente
     * 
     * Se espera:
     * - Retornar lista vacía
     */
    @Test
    public void testConsultarAsistenciaNoExistente() {

        ArrayList<Asistencia> asistencias =
                servicioAsistencia
                        .consultarAsistencia("999");

        assertTrue(asistencias.isEmpty());
    }
}