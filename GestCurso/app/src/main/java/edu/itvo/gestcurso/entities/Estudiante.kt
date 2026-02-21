package edu.itvo.gestcurso.entities

data class Estudiante(
    val id: String,
    val nombre: String,
    val cursosInscritos: MutableList<Curso> = mutableListOf()
)