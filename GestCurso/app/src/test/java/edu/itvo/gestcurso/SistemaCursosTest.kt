package edu.itvo.gestcurso

import org.junit.Test
import org.junit.Assert.*
import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.entities.Profesor
import edu.itvo.gestcurso.logic.SistemaCursos
import edu.itvo.gestcurso.repositories.CursoRepositoryMemoria
import edu.itvo.gestcurso.repositories.EstudianteRepositoryMemoria
import edu.itvo.gestcurso.rules.ReglaCapacidadMaxima
import edu.itvo.gestcurso.rules.ReglaInscripcionDuplicada

class SistemaCursosTest {

    @Test
    fun `prueba de flujo completo inscripcion exitosa`() {
        val repoCursos = CursoRepositoryMemoria()
        val repoEstudiantes = EstudianteRepositoryMemoria()
        val reglas = listOf(ReglaCapacidadMaxima(), ReglaInscripcionDuplicada())
        val sistema = SistemaCursos(repoCursos, repoEstudiantes, reglas)
        val curso = Curso("C1", "Kotlin", Profesor("P", "X"))
        val estudiante = Estudiante("E1", "Ismael")
        repoCursos.guardar(curso)
        repoEstudiantes.guardar(estudiante)
        val mensaje = sistema.inscribirEstudiante("E1", "C1")
        assertEquals("Éxito", mensaje)
        assertEquals(1, curso.estudiantesInscritos.size)
        assertEquals("Ismael", curso.estudiantesInscritos[0].nombre)
    }
    @Test
    fun `prueba de fallo por estudiante no encontrado`() {
        val repoCursos = CursoRepositoryMemoria()
        val repoEstudiantes = EstudianteRepositoryMemoria() // Vacío
        val reglas = listOf(ReglaCapacidadMaxima())
        val sistema = SistemaCursos(repoCursos, repoEstudiantes, reglas)
        val mensaje = sistema.inscribirEstudiante("E999", "C1")
        assertEquals("Estudiante no existe", mensaje)
    }
}