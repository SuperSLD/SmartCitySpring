package online.jutter.smartcity.domain

import online.jutter.smartcity.data.db.ent.CompetitionEntity
import online.jutter.smartcity.data.db.repositories.CompetitionsRepository

class GetScheduleUseCase {

    operator fun invoke(): HashMap<String, HashMap<String, MutableList<CompetitionEntity>>> {
        val unsortedList = CompetitionsRepository.getAll()

        val sortData = hashMapOf<String, HashMap<String, MutableList<CompetitionEntity>>>()

        unsortedList.forEach { comp ->
            if (sortData[comp.getDay()] == null) {
                sortData[comp.getDay()] = hashMapOf()
            }

            if (sortData[comp.getDay()]!![comp.getTime()] == null) {
                sortData[comp.getDay()]!![comp.getTime()] = mutableListOf()
            }

            sortData[comp.getDay()]!![comp.getTime()]!!.add(comp)
        }

        return sortData
    }
}