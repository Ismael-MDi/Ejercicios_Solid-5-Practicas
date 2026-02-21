package edu.itvo.eventos.repositories
import edu.itvo.eventos.entities.Registration
import edu.itvo.eventos.interfaces.IRegistrationRepository

class RegistrationRepositoryMemory : IRegistrationRepository {
    private val list = mutableListOf<Registration>()

    override fun save(registration: Registration) {
        list.add(registration)
    }
    override fun getByActivity(activityId: String): List<Registration> {
        val result = mutableListOf<Registration>()
        for (reg in list) {
            if (reg.activityId == activityId) {
                result.add(reg)
            }
        }
        return result
    }
    override fun getByAttendee(attendeeId: String): List<Registration> {
        val result = mutableListOf<Registration>()
        for (reg in list) {
            if (reg.attendeeId == attendeeId) {
                result.add(reg)
            }
        }
        return result
    }
}