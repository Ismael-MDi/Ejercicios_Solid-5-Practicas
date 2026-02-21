package edu.itvo.gestcurso

import org.junit.Test
import org.junit.Assert.*
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.repositories.EstudianteRepositoryMemoria

class EstudianteRepositoryTest {

    @Test
    fun `debe guardar y encontrar estudiante por id`() {
        val repo = EstudianteRepositoryMemoria()
        val estudiante = Estudiante("E001", "Ismael Morales")
        repo.guardar(estudiante)
        val encontrado = repo.buscarPorId("E001")
        assertNotNull(encontrado)
        assertEquals("Ismael Morales", encontrado?.nombre)
    }
}