package edu.itvo.eventos.repositories
import edu.itvo.eventos.entities.Activity
import edu.itvo.eventos.interfaces.IActivityRepository

class ActivityRepositoryMemory : IActivityRepository {
    private val list = mutableListOf<Activity>()

    override fun save(activity: Activity) {
        list.add(activity)
    }
    override fun findById(id: String): Activity? {
        for (activity in list) {
            if (activity.id == id) return activity
        }
        return null
    }
}