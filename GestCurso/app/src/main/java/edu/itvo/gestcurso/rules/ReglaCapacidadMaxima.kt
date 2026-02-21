package edu.itvo.gestcurso.rules
import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.interfaces.IReglaInscripcion

class ReglaCapacidadMaxima : IReglaInscripcion {
    override fun validar(curso: Curso, estudiante: Estudiante): Boolean {
        if (curso.estudiantesInscritos.size < 30) {
            return true
        } else {
            return false
        }
    }
    override fun mensajeError() = "Cupo lleno (Máx 30)"
}