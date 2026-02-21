package edu.itvo.gestcurso

import org.junit.Test
import org.junit.Assert.*
import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.entities.Profesor
import edu.itvo.gestcurso.rules.ReglaCapacidadMaxima

class ReglaCapacidadMaximaTest {
    @Test
    fun `debe permitir inscripcion si hay espacio`() {
        val regla = ReglaCapacidadMaxima()
        val curso = Curso("C1", "Curso Vacío", Profesor("P", "X"))
        val estudiante = Estudiante("E1", "Alumno")

        assertTrue(regla.validar(curso, estudiante))
    }
    @Test
    fun `debe bloquear si tiene 30 alumnos`() {
        val regla = ReglaCapacidadMaxima()
        val curso = Curso("C1", "Curso Lleno", Profesor("P", "X"))
        val alumnoNuevo = Estudiante("E_NEW", "Nuevo")
        for (i in 1..30) {
            curso.estudiantesInscritos.add(Estudiante("E$i", "Alumno $i"))
        }
        assertFalse(regla.validar(curso, alumnoNuevo))
        assertEquals("Cupo lleno (Máx 30)", regla.mensajeError())
    }
}