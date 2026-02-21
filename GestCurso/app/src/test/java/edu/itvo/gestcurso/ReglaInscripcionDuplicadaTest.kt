package edu.itvo.gestcurso

import org.junit.Test
import org.junit.Assert.*
import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.entities.Profesor
import edu.itvo.gestcurso.rules.ReglaInscripcionDuplicada

class ReglaInscripcionDuplicadaTest {
    @Test
    fun `debe permitir si el alumno es nuevo en el curso`() {
        val regla = ReglaInscripcionDuplicada()
        val curso = Curso("C1", "Materia", Profesor("P", "X"))
        val alumno = Estudiante("E1", "Juan")
        assertTrue(regla.validar(curso, alumno))
    }

    @Test
    fun `debe bloquear si el alumno ya existe`() {
        val regla = ReglaInscripcionDuplicada()
        val curso = Curso("C1", "Materia", Profesor("P", "X"))
        val alumno = Estudiante("E1", "Juan")
        curso.estudiantesInscritos.add(alumno)
        val resultado = regla.validar(curso, alumno)
        assertFalse(resultado)
        assertEquals("Ya está inscrito", regla.mensajeError())
    }
}