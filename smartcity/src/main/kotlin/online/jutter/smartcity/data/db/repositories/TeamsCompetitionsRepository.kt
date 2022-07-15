package online.jutter.smartcity.data.db.repositories

import online.jutter.smartcity.common.ext.getQuery
import online.jutter.smartcity.data.db.BaseRepository
import online.jutter.smartcity.data.db.ent.NewsEntity
import online.jutter.smartcity.data.db.ent.TeamCompetitionEntity
import online.jutter.smartcity.data.db.ent.TeamsEntity
import online.jutter.smartcity.data.db.ent.UserEntity
import online.jutter.smartcity.data.db.eq
import online.jutter.smartcity.net.TeamsController

object TeamsCompetitionsRepository : BaseRepository<TeamCompetitionEntity>() {

    fun getByUser(user: UserEntity) = executeTransaction {
        if (user.teamId != null)
            getQuery<TeamCompetitionEntity>("id" eq user.teamId!!)
        else
            null
    }

    fun join(phone: String, id: Int) = executeTransaction {
        val user = UsersRepository.getUser(phone)
        val competition = CompetitionsRepository.getById(id)

        if (competition?.sport?.isTeam == true && user?.teamId == null) {
            error("Нужно найти команду")
        } else if (competition?.sport?.isTeam == true && user?.teamId != null) {
            val query = createSQLQuery(
                "INSERT INTO admin_hakaton_teams_competitions (team_id, competition_id, rank_team) VALUES (${user!!.teamId}, ${competition?.id}, 0)"
            )
            query.executeUpdate()
        } else if (competition?.sport?.isTeam == false) {
            val query = createSQLQuery(
                "INSERT INTO admin_hakaton_teams_competitions (team_id, competition_id, rank_team) VALUES (${user!!.id}, ${competition?.id}, 0)"
            )
            query.executeUpdate()
        }
        null
    }
}
