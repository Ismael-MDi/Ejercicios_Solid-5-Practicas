package edu.itvo.eventos
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import edu.itvo.eventos.entities.Registration
import edu.itvo.eventos.repositories.RegistrationRepositoryMemory

class RegistrationRepositoryTest {
    private lateinit var repo: RegistrationRepositoryMemory
    @Before
    fun prepararDatos() {
        repo = RegistrationRepositoryMemory()
        repo.save(Registration("REG-1", "A1", "U1"))
        repo.save(Registration("REG-2", "A1", "U2"))
    }
    @Test
    fun `should get registrations by activity`() {
        val lista = repo.getByActivity("A1")
        assertEquals(2, lista.size)
    }
    @Test
    fun `should get registrations by attendee`() {
        val lista = repo.getByAttendee("U1")
        assertEquals(1, lista.size)
        assertEquals("A1", lista[0].activityId)
    }
}