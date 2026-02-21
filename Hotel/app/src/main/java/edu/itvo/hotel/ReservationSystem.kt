package edu.itvo.hotel
import android.os.Build
import java.time.LocalDate

class ReservationSystem(private val hotel: Hotel) {

    fun createReservation(guest: Guest, room: Room, checkIn: LocalDate, checkOut: LocalDate): Boolean {
        // Check availability
        if (!isRoomAvailable(room, checkIn, checkOut)) {
            println("❌ Room ${room.number} is not available from $checkIn to $checkOut.")
            return false
        }

        val reservation = Reservation(room, guest, checkIn, checkOut)
        hotel.reservations.add(reservation)
        guest.reservations.add(reservation)
        room.available = false

        println("✅ Reservation created for ${guest.name} in room ${room.number}.")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            println("Total cost: $${reservation.totalCost()}")
        }
        return true
    }

    fun cancelReservation(reservation: Reservation) {
        if (hotel.reservations.remove(reservation)) {
            reservation.guest.reservations.remove(reservation)
            reservation.room.available = true
            println("🗑️ Reservation for ${reservation.guest.name} in room ${reservation.room.number} has been cancelled.")
        } else {
            println("⚠️ Reservation not found.")
        }
    }

    fun isRoomAvailable(room: Room, checkIn: LocalDate, checkOut: LocalDate): Boolean {
        return hotel.reservations.none { reservation ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                reservation.room == room &&
                        checkIn < reservation.checkOut &&
                        checkOut > reservation.checkIn
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        }
    }


    fun showReservations() {
        println("\n📋 Current Reservations:")
        if (hotel.reservations.isEmpty()) {
            println("No reservations.")
        } else {
            hotel.reservations.forEach {
                println("Room ${it.room.number} | Guest: ${it.guest.name} | ${it.checkIn} to ${it.checkOut} | Total: $${it.totalCost()}")
            }
        }
    }

    fun showAvailableRooms() {
        println("\n🏨 Available Rooms:")
        val availableRooms = hotel.rooms.filter { room -> isRoomAvailable(room, LocalDate.now(), LocalDate.now().plusDays(1)) }
        if (availableRooms.isEmpty()) {
            println("No rooms available.")
        } else {
            availableRooms.forEach {
                println("Room ${it.number} | Type: ${it.type} | Price: $${it.pricePerNight}/night")
            }
        }
    }
}
