package edu.itvo.eventos.logic
import edu.itvo.eventos.entities.Activity
import edu.itvo.eventos.entities.Attendee
import edu.itvo.eventos.entities.Registration
import edu.itvo.eventos.interfaces.IActivityRepository
import edu.itvo.eventos.interfaces.IAttendeeRepository
import edu.itvo.eventos.interfaces.IRegistrationRepository
class EventSystem(
    private val activityRepo: IActivityRepository,
    private val attendeeRepo: IAttendeeRepository,
    private val registrationRepo: IRegistrationRepository
) {
    fun enroll(attendeeId: String, activityId: String): String {
        val attendee = attendeeRepo.findById(attendeeId)
        val newActivity = activityRepo.findById(activityId)
        if (attendee == null) return "Error: Asistente no encontrado"
        if (newActivity == null) return "Error: Actividad no encontrada"
        val currentRegistrations = registrationRepo.getByActivity(activityId)
        var totalEnrolled = 0
        for (reg in currentRegistrations) {
            totalEnrolled = totalEnrolled + 1
        }
        if (totalEnrolled >= newActivity.maxCapacity) {
            return "Error: Cupo lleno para la actividad '${newActivity.name}'"
        }
        val attendeeRegistrations = registrationRepo.getByAttendee(attendeeId)
        for (reg in attendeeRegistrations) {
            val enrolledActivity = activityRepo.findById(reg.activityId)
            if (enrolledActivity != null) {
                if (newActivity.startTime < enrolledActivity.endTime) {
                    if (newActivity.endTime > enrolledActivity.startTime) {
                        return "Error: Choque de horario con la actividad '${enrolledActivity.name}'"
                    }
                }
            }
        }
        val newRegistration = Registration(
            id = "REG-" + System.currentTimeMillis(),
            activityId = activityId,
            attendeeId = attendeeId
        )
        registrationRepo.save(newRegistration)
        attendee.enrolledActivities.add(newActivity)
        return "Éxito: Inscrito correctamente a '${newActivity.name}'"
    }
    fun getAttendeeSchedule(attendeeId: String): List<Activity> {
        val attendee = attendeeRepo.findById(attendeeId)
        if (attendee != null) {
            return attendee.enrolledActivities
        }
        return emptyList()
    }
    fun getAttendeesByActivity(activityId: String): List<Attendee> {
        val registrations = registrationRepo.getByActivity(activityId)
        val attendeesList = mutableListOf<Attendee>()
        for (reg in registrations) {
            val att = attendeeRepo.findById(reg.attendeeId)
            if (att != null) {
                attendeesList.add(att)
            }
        }
        return attendeesList
    }
}