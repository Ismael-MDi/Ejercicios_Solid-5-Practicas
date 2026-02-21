package edu.itvo.gestcurso.interfaces
import edu.itvo.gestcurso.entities.Curso

interface ICursoRepository {
    fun buscarPorCodigo(codigo: String): Curso?
    fun guardar(curso: Curso)
}