package edu.itvo.hotel
data class Hotel(
    val name: String,
    val rooms: MutableList<Room> = mutableListOf(),
    val reservations: MutableList<Reservation> = mutableListOf()
)
