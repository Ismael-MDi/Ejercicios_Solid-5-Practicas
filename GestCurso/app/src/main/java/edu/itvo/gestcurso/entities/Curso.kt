package edu.itvo.gestcurso.entities

data class Curso(
    val codigo: String,
    val nombre: String,
    val profesor: Profesor,
    val estudiantesInscritos: MutableList<Estudiante> = mutableListOf()
)