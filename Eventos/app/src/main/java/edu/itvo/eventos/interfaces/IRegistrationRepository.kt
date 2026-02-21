package edu.itvo.eventos.interfaces
import edu.itvo.eventos.entities.Registration
interface IRegistrationRepository {
    fun save(registration: Registration)
    fun getByActivity(activityId: String): List<Registration>
    fun getByAttendee(attendeeId: String): List<Registration>
}