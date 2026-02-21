package edu.itvo.gestcurso.interfaces
import edu.itvo.gestcurso.entities.Estudiante

interface IEstudianteRepository {
    fun buscarPorId(id: String): Estudiante?
    fun guardar(estudiante: Estudiante)
}