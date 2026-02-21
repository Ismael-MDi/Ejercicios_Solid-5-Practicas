package edu.itvo.gestcurso

import org.junit.Test
import org.junit.Assert.*
import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Profesor
import edu.itvo.gestcurso.repositories.CursoRepositoryMemoria

class CursoRepositoryTest {
    @Test
    fun `debe guardar y encontrar curso por codigo`() {
        val repo = CursoRepositoryMemoria()
        val profe = Profesor("Turing", "IA")
        val curso = Curso("C101", "Inteligencia Artificial", profe)
        repo.guardar(curso)
        val encontrado = repo.buscarPorCodigo("C101")
        assertNotNull(encontrado)
        assertEquals("Inteligencia Artificial", encontrado?.nombre)
    }
    @Test
    fun `debe devolver null si el curso no existe`() {
        val repo = CursoRepositoryMemoria()
        val encontrado = repo.buscarPorCodigo("Z999") // No existe
        assertNull(encontrado)
    }
}