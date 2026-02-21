package edu.itvo.gestcurso.repositories
import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.interfaces.ICursoRepository

class CursoRepositoryMemoria : ICursoRepository {
    private val lista = mutableListOf<Curso>()

    override fun guardar(curso: Curso) {
        lista.add(curso)
    }
    override fun buscarPorCodigo(codigo: String): Curso? {
        for (c in lista) {
            if (c.codigo == codigo) {
                return c
            }
        }
        return null
    }
}