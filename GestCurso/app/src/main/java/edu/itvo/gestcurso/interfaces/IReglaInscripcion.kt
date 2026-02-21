package edu.itvo.gestcurso.interfaces
import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Estudiante

interface IReglaInscripcion {
    fun validar(curso: Curso, estudiante: Estudiante): Boolean
    fun mensajeError(): String
}