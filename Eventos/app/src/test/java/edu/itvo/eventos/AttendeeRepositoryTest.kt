package edu.itvo.eventos
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import edu.itvo.eventos.entities.Attendee
import edu.itvo.eventos.repositories.AttendeeRepositoryMemory

class AttendeeRepositoryTest {
    private lateinit var repo: AttendeeRepositoryMemory
    @Before
    fun prepararDatos() {
        repo = AttendeeRepositoryMemory()
        repo.save(Attendee("U1", "Ismael", "correo"))
    }
    @Test
    fun `should find attendee by id`() {
        val found = repo.findById("U1")
        assertNotNull(found)
        assertEquals("Ismael", found!!.name)
    }
}