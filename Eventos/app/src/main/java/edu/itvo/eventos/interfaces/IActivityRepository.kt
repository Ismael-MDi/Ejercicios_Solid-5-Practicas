package edu.itvo.eventos.interfaces
import edu.itvo.eventos.entities.Activity
interface IActivityRepository {
    fun save(activity: Activity)
    fun findById(id: String): Activity?
}