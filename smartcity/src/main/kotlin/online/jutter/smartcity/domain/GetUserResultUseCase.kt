package online.jutter.smartcity.domain

import online.jutter.smartcity.data.db.ent.UserEntity
import online.jutter.smartcity.data.db.repositories.TeamsCompetitionsRepository

class GetUserResultUseCase {

    operator fun invoke(user: UserEntity): List<Int> {
        var gold = 0
        var silver = 0
        var bronze  = 0
        var other = 0
        TeamsCompetitionsRepository.getByUser(user)!!.forEach {
            if (it.rank == 1 || it.rank == -1) {
                gold++
            } else if (it.rank == 2 || it.rank == -2) {
                silver++
            } else if (it.rank == 3 || it.rank == -3) {
                bronze++
            } else if (it.rank != 0 && it.rank != null) {
                other++
            }
        }
        return listOf(gold, silver, bronze, other)
    }
}