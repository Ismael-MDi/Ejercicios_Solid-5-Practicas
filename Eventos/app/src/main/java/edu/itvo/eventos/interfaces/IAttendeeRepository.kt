package edu.itvo.eventos.interfaces
import edu.itvo.eventos.entities.Attendee
interface IAttendeeRepository {
    fun save(attendee: Attendee)
    fun findById(id: String): Attendee?
}