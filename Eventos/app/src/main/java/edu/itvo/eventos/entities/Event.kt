package edu.itvo.eventos.entities
data class Event(
    val name: String,
    val date: String,
    val activities: MutableList<Activity> = mutableListOf()
)