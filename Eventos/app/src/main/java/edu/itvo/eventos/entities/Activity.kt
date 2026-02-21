package edu.itvo.eventos.entities
data class Activity(
    val id: String,
    val name: String,
    val speaker: String,
    val startTime: Int,
    val endTime: Int,
    val maxCapacity: Int
)