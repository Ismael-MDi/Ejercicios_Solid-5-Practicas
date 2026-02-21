package edu.itvo.gestcurso

import edu.itvo.gestcurso.entities.Curso
import edu.itvo.gestcurso.entities.Estudiante
import edu.itvo.gestcurso.entities.Profesor
import edu.itvo.gestcurso.logic.SistemaCursos
import edu.itvo.gestcurso.repositories.CursoRepositoryMemoria
import edu.itvo.gestcurso.repositories.EstudianteRepositoryMemoria
import edu.itvo.gestcurso.rules.ReglaCapacidadMaxima
import edu.itvo.gestcurso.rules.ReglaInscripcionDuplicada

fun main() {
    val repoCursos = CursoRepositoryMemoria()
    val repoEstudiantes = EstudianteRepositoryMemoria()
    val profeYair = Profesor("Hiber Yair", "INT Negocios")
    val profeCardoso = Profesor("Ambrosio Cardoso", "APPS Moviles 2")
    val curso1 = Curso("C101", "Apps Moviles", profeYair)
    val curso2 = Curso("C102", "Negocios", profeCardoso)
    repoCursos.guardar(curso1)
    repoCursos.guardar(curso2)
    val estudiante1 = Estudiante("E001", "Ismael Morales")
    val estudiante2 = Estudiante("E002", "Julián Paz")
    repoEstudiantes.guardar(estudiante1)
    repoEstudiantes.guardar(estudiante2)
    val misReglas = listOf(
        ReglaCapacidadMaxima(),
        ReglaInscripcionDuplicada()
    )
    val sistema = SistemaCursos(repoCursos, repoEstudiantes, misReglas)
    print("Inscribiendo a Ismael en Apps Moviles: ")
    val resultado1 = sistema.inscribirEstudiante("E001", "C101")
    println(resultado1)
    print("Inscribiendo a Julián en Apps Moviles: ")
    val resultado2 = sistema.inscribirEstudiante("E002", "C101")
    println(resultado2)
    print("Re-inscribiendo a Ismael en Apps Moviles: ")
    val resultadoDuplicado = sistema.inscribirEstudiante("E001", "C101")
    println(resultadoDuplicado)
    print("Inscribiendo a Ismael en Negocios: ")
    val resultado3 = sistema.inscribirEstudiante("E001", "C102")
    println(resultado3)
    println("\n REPORTE: Estudiantes en el curso de Apps Moviles (C101)")
    val listaAlumnos = sistema.obtenerEstudiantesDeCurso("C101")
    for (alumno in listaAlumnos) {
        println("- ID: ${alumno.id} | Nombre: ${alumno.nombre}")
    }
    println("\n REPORTE: Cursos de Ismael (E001)")
    val listaCursos = sistema.obtenerCursosDeEstudiante("E001")
    for (curso in listaCursos) {
        println("- Código: ${curso.codigo} | Materia: ${curso.nombre} | Profe: ${curso.profesor.nombre}")
    }
}