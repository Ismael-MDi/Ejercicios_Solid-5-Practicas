package edu.itvo.eventos.entities
data class Attendee(
    val id: String,
    val name: String,
    val email: String,
    val enrolledActivities: MutableList<Activity> = mutableListOf()
)