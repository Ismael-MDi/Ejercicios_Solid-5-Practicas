package edu.itvo.eventos.repositories
import edu.itvo.eventos.entities.Attendee
import edu.itvo.eventos.interfaces.IAttendeeRepository

class AttendeeRepositoryMemory : IAttendeeRepository {
    private val list = mutableListOf<Attendee>()

    override fun save(attendee: Attendee) {
        list.add(attendee)
    }
    override fun findById(id: String): Attendee? {
        for (attendee in list) {
            if (attendee.id == id) return attendee
        }
        return null
    }
}