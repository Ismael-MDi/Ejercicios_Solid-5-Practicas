package edu.itvo.gestcurso.logic

import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.interfaces.ICursoRepository
import edu.itvo.gestcurso.interfaces.IEstudianteRepository
import edu.itvo.gestcurso.interfaces.IReglaInscripcion

class SistemaCursos(
    private val repoCursos: ICursoRepository,
    private val repoEstudiantes: IEstudianteRepository,
    private val reglas: List<IReglaInscripcion>
) {
    fun inscribirEstudiante(idEstudiante: String, codigoCurso: String): String {
        val estudiante = repoEstudiantes.buscarPorId(idEstudiante)
        val curso = repoCursos.buscarPorCodigo(codigoCurso)
        if (estudiante == null) return "Estudiante no existe"
        if (curso == null) return "Curso no existe"
        for (regla in reglas) {
            val esValido = regla.validar(curso, estudiante)
            if (esValido == false) {
                return "Error: " + regla.mensajeError()
            }
        }
        curso.estudiantesInscritos.add(estudiante)
        estudiante.cursosInscritos.add(curso)
        return "Éxito"
    }
    fun obtenerEstudiantesDeCurso(codigo: String): List<Estudiante> {
        val curso = repoCursos.buscarPorCodigo(codigo)
        if (curso != null) {
            return curso.estudiantesInscritos
        }
        return emptyList()
    }
    fun obtenerCursosDeEstudiante(id: String): List<Curso> {
        val estudiante = repoEstudiantes.buscarPorId(id)
        if (estudiante != null) {
            return estudiante.cursosInscritos
        }
        return emptyList()
    }
}