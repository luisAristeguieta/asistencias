package com.krakedev.asistencias.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.servicios.ServicioEstudiantes;

public class ServicioEstudiantesTest {

    private ServicioEstudiantes servicio;

    /**
     * Se ejecuta antes de cada prueba.
     * Inicializa el servicio con una lista vacía.
     */
    @BeforeEach
    public void setUp() {
        servicio = new ServicioEstudiantes();
    }

    /**
     * ESCENARIO: Agregar un estudiante nuevo
     * 
     * Se espera:
     * - El estudiante se agregue correctamente
     * - La lista tenga un elemento
     */
    @Test
    public void testAgregarEstudiante() {

        Estudiante e1 = new Estudiante(
                "123",
                "Juan",
                "Perez"
        );

        servicio.agregar(e1);

        assertEquals(1, servicio.listar().size());
    }

    /**
     * ESCENARIO: Intentar agregar un estudiante duplicado
     * 
     * Se espera:
     * - No se agregue nuevamente
     * - La lista mantenga un solo elemento
     */
    @Test
    public void testAgregarDuplicado() {

        Estudiante e1 = new Estudiante(
                "123",
                "Juan",
                "Perez"
        );

        servicio.agregar(e1);
        servicio.agregar(e1);

        assertEquals(1, servicio.listar().size());
    }

    /**
     * ESCENARIO: Buscar un estudiante existente
     * 
     * Se espera:
     * - Encontrar el estudiante
     * - Validar sus datos
     */
    @Test
    public void testBuscarPorCedulaExistente() {

        Estudiante e1 = new Estudiante(
                "123",
                "Juan",
                "Perez"
        );

        servicio.agregar(e1);

        Estudiante encontrado =
                servicio.buscarPorCedula("123");

        assertNotNull(encontrado);
        assertEquals("Juan", encontrado.getNombre());
        assertEquals("Perez", encontrado.getApellido());
    }

    /**
     * ESCENARIO: Buscar un estudiante inexistente
     * 
     * Se espera:
     * - Retornar null
     */
    @Test
    public void testBuscarPorCedulaNoExistente() {

        Estudiante encontrado =
                servicio.buscarPorCedula("999");

        assertNull(encontrado);
    }

    /**
     * ESCENARIO: Actualizar un estudiante existente
     * 
     * Se espera:
     * - Cambiar nombre y apellido
     */
    @Test
    public void testActualizarEstudiante() {

        Estudiante e1 = new Estudiante(
                "123",
                "Juan",
                "Perez"
        );

        servicio.agregar(e1);

        Estudiante nuevo = new Estudiante(
                "123",
                "Carlos",
                "Lopez"
        );

        servicio.actualizar("123", nuevo);

        Estudiante actualizado =
                servicio.buscarPorCedula("123");

        assertEquals("Carlos",
                actualizado.getNombre());

        assertEquals("Lopez",
                actualizado.getApellido());
    }

    /**
     * ESCENARIO: Eliminar un estudiante existente
     * 
     * Se espera:
     * - El estudiante sea eliminado
     * - La lista quede vacía
     */
    @Test
    public void testEliminarEstudiante() {

        Estudiante e1 = new Estudiante(
                "123",
                "Juan",
                "Perez"
        );

        servicio.agregar(e1);

        servicio.eliminar("123");

        assertEquals(0, servicio.listar().size());
    }

    /**
     * ESCENARIO: Eliminar un estudiante inexistente
     * 
     * Se espera:
     * - No generar errores
     * - Mantener la lista vacía
     */
    @Test
    public void testEliminarNoExistente() {

        servicio.eliminar("999");

        assertEquals(0, servicio.listar().size());
    }

    /**
     * ESCENARIO: Listar estudiantes
     * 
     * Se espera:
     * - Retornar todos los estudiantes agregados
     */
    @Test
    public void testListarEstudiantes() {

        Estudiante e1 = new Estudiante(
                "123",
                "Juan",
                "Perez"
        );

        Estudiante e2 = new Estudiante(
                "456",
                "Carlos",
                "Lopez"
        );

        servicio.agregar(e1);
        servicio.agregar(e2);

        assertEquals(2, servicio.listar().size());
    }
}