package edu.itvo.eventos

import edu.itvo.eventos.entities.Activity
import edu.itvo.eventos.entities.Attendee
import edu.itvo.eventos.logic.EventSystem
import edu.itvo.eventos.repositories.ActivityRepositoryMemory
import edu.itvo.eventos.repositories.AttendeeRepositoryMemory
import edu.itvo.eventos.repositories.RegistrationRepositoryMemory

fun main() {
    println("SISTEMA DE GESTION DE EVENTOS")
    val actRepo = ActivityRepositoryMemory()
    val attRepo = AttendeeRepositoryMemory()
    val regRepo = RegistrationRepositoryMemory()
    val act1 = Activity("A1", "Taller Kotlin", "Hiber Yair", 10, 12, 2)
    val act2 = Activity("A2", "Taller de Negocios", "Julian Pas", 11, 13, 5)
    val act3 = Activity("A3", "Desarrollo Movil", "Ambrosio Cardoso", 13, 15, 2)
    actRepo.save(act1)
    actRepo.save(act2)
    actRepo.save(act3)
    val att1 = Attendee("U1", "Ismael Morales", "ismael@mail.com")
    val att2 = Attendee("U2", "Julián Paz", "julian@mail.com")
    val att3 = Attendee("U3", "Juan Perez", "juan@mail.com")
    attRepo.save(att1)
    attRepo.save(att2)
    attRepo.save(att3)
    val system = EventSystem(actRepo, attRepo, regRepo)
    println("\nIntento 1: Ismael se inscribe a Taller Kotlin (10 a 12)")
    println(system.enroll("U1", "A1"))
    println("\nIntento 2: Ismael intenta inscribirse a Taller Negocios (11 a 13)")
    println(system.enroll("U1", "A2"))
    println("\nIntento 3: Ismael se inscribe a Desarrollo Móvil (13 a 15)")
    println(system.enroll("U1", "A3"))
    println("\nIntento 4: Llenar el cupo del Taller Kotlin (Solo caben 2)")
    println(system.enroll("U2", "A1"))
    println(system.enroll("U3", "A1"))
    println("\nCRONOGRAMA DE ISMAEL")
    val schedule = system.getAttendeeSchedule("U1")
    for (act in schedule) {
        println("- ${act.name} (Horario: ${act.startTime}:00 a ${act.endTime}:00)")
    }
}