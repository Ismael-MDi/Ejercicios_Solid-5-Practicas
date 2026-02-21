package edu.itvo.events
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import edu.itvo.eventos.entities.Activity
import edu.itvo.eventos.entities.Attendee
import edu.itvo.eventos.logic.EventSystem
import edu.itvo.eventos.repositories.ActivityRepositoryMemory
import edu.itvo.eventos.repositories.AttendeeRepositoryMemory
import edu.itvo.eventos.repositories.RegistrationRepositoryMemory
class EventSystemTest {
    private lateinit var actRepo: ActivityRepositoryMemory
    private lateinit var attRepo: AttendeeRepositoryMemory
    private lateinit var regRepo: RegistrationRepositoryMemory
    private lateinit var system: EventSystem
    @Before
    fun prepararDatos() {
        actRepo = ActivityRepositoryMemory()
        attRepo = AttendeeRepositoryMemory()
        regRepo = RegistrationRepositoryMemory()
        system = EventSystem(actRepo, attRepo, regRepo)
        actRepo.save(Activity("A1", "Kotlin", "Profe 1", 10, 12, 1))
        actRepo.save(Activity("A2", "Java", "Profe 2", 11, 13, 5))

        attRepo.save(Attendee("U1", "Ismael", "correo1"))
        attRepo.save(Attendee("U2", "Juan", "correo2"))
    }
    @Test
    fun `should enroll successfully and update lists`() {
        system.enroll("U1", "A1")
        val ismael = attRepo.findById("U1")
        assertEquals(1, ismael!!.enrolledActivities.size)
        assertEquals("Kotlin", ismael.enrolledActivities[0].name)
        val registros = regRepo.getByActivity("A1")
        assertEquals(1, registros.size)
    }
    @Test
    fun `should fail if schedule overlaps and keep lists empty`() {
        system.enroll("U1", "A1")
        system.enroll("U1", "A2")
        val ismael = attRepo.findById("U1")
        assertEquals(1, ismael!!.enrolledActivities.size)
        val registrosA2 = regRepo.getByActivity("A2")
        assertEquals(0, registrosA2.size)
    }
    @Test
    fun `should fail if capacity is full and block registration`() {
        system.enroll("U1", "A1")
        system.enroll("U2", "A1")
        val juan = attRepo.findById("U2")
        assertEquals(0, juan!!.enrolledActivities.size)
        val registros = regRepo.getByActivity("A1")
        assertEquals(1, registros.size)
    }
}