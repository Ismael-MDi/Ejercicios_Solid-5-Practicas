package edu.itvo.gestcurso.rules
import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.interfaces.IReglaInscripcion

class ReglaInscripcionDuplicada : IReglaInscripcion {
    override fun validar(curso: Curso, estudiante: Estudiante): Boolean {
        for (inscrito in curso.estudiantesInscritos) {
            if (inscrito.id == estudiante.id) {
                return false
            }
        }
        return true
    }
    override fun mensajeError() = "Ya está inscrito"
}