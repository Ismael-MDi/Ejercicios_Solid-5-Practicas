package edu.itvo.eventos
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import edu.itvo.eventos.entities.Activity
import edu.itvo.eventos.repositories.ActivityRepositoryMemory

class ActivityRepositoryTest {
    private lateinit var repo: ActivityRepositoryMemory

    @Before
    fun prepararDatos() {
        repo = ActivityRepositoryMemory()
        repo.save(Activity("A1", "Taller Android", "Spk", 10, 12, 5))
    }
    @Test
    fun `should find activity by id`() {
        val found = repo.findById("A1")

        assertNotNull(found)
        assertEquals("Taller Android", found!!.name)
    }
}