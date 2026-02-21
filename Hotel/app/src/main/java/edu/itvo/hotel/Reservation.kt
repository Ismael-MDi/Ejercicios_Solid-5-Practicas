package edu.itvo.hotel
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Reservation(
    val room: Room,
    val guest: Guest,
    val checkIn: LocalDate,
    val checkOut: LocalDate
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun totalCost(): Double {
        val nights = ChronoUnit.DAYS.between(checkIn, checkOut).toInt()

        if (nights <= 0) {
            throw IllegalArgumentException("Check-out date must be after check-in date")
        }

        return nights * room.pricePerNight
    }
}