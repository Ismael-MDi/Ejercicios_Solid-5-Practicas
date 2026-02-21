package edu.itvo.gestcurso.repositories
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.interfaces.IEstudianteRepository

class EstudianteRepositoryMemoria : IEstudianteRepository {
    private val lista = mutableListOf<Estudiante>()
    override fun guardar(estudiante: Estudiante) {
        lista.add(estudiante)
    }
    override fun buscarPorId(id: String): Estudiante? {
        for (e in lista) {
            if (e.id == id) {
                return e
            }
        }
        return null
    }
}