package online.jutter.smartcity.domain

import online.jutter.smartcity.data.db.repositories.TeamsCompetitionsRepository
import online.jutter.smartcity.data.db.repositories.TeamsRepository
import online.jutter.smartcity.data.db.repositories.UsersRepository
import online.jutter.smartcity.data.models.LeaderBoardResponse
import online.jutter.smartcity.data.models.PositionResponse
import kotlin.math.abs

class GetLeaderBoardUseCase {

    operator fun invoke(): MutableCollection<LeaderBoardResponse> {
        val teamsResult = TeamsCompetitionsRepository.getAll()

        val leaderBoardList = hashMapOf<Int, LeaderBoardResponse>()

        teamsResult.forEach { tr ->
            if (leaderBoardList[tr.competition.id] == null) {
                leaderBoardList[tr.competition.id] = LeaderBoardResponse(
                    competition = tr.competition,
                    list = mutableListOf(),
                )
            }

            if (tr.rank != null && tr.rank!! < 0) {
                val user = UsersRepository.getUserById(tr.teamId)
                leaderBoardList[tr.competition.id]!!.list.add(
                    PositionResponse(
                        rank = abs(tr.rank!!),
                        name = "${user!!.firstname} ${user.lastname}"
                    )
                )
            }

            if (tr.rank != null && tr.rank!! > 0) {
                val team = TeamsRepository.getTeamById(tr.teamId)
                leaderBoardList[tr.competition.id]!!.list.add(
                    PositionResponse(
                        rank = abs(tr.rank!!),
                        name = team?.name!!
                    )
                )
            }
        }

        val resultList = leaderBoardList.values.filter { it.list.isNotEmpty() }.toMutableList()
        resultList.forEach { it.list.sortBy { it.rank } }
        return resultList
    }
}