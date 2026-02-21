package edu.itvo.hotel
import java.util.Calendar

fun main() {
    val hotel = Hotel("Grand Plaza")

    // Add rooms
    val room101 = Room(101, "Single", 50.0)
    val room102 = Room(102, "Double", 80.0)
    val room103 = Room(103, "Suite", 150.0)

    hotel.rooms.addAll(listOf(room101, room102, room103))

    // Guests
    val guest1 = Guest("Alice Johnson", "ID123")
    val guest2 = Guest("Bob Smith", "ID456")

    val system = ReservationSystem(hotel)

    // Create reservations
    val checkIn = Calendar.getInstance().apply { set(2025, Calendar.SEPTEMBER, 20) }.timeInMillis
    val checkOut = Calendar.getInstance().apply { set(2025, Calendar.SEPTEMBER, 23) }.timeInMillis
    // Show reservations
    system.showReservations()

    // Cancel reservation
    val reservationToCancel = guest1.reservations[0]
    system.cancelReservation(reservationToCancel)

    // Show available rooms again
    system.showAvailableRooms()
}
