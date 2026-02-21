package edu.itvo.hotel

data class Guest(
    val name: String,
    val id: String,
    val reservations: MutableList<Reservation> = mutableListOf()
)
