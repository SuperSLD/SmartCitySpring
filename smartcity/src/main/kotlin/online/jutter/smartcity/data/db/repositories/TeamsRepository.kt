package online.jutter.smartcity.data.db.repositories

import online.jutter.smartcity.common.ext.getQuery
import online.jutter.smartcity.data.db.BaseRepository
import online.jutter.smartcity.data.db.ent.NewsEntity
import online.jutter.smartcity.data.db.ent.TeamsEntity
import online.jutter.smartcity.data.db.ent.UserEntity
import online.jutter.smartcity.data.db.eq
import online.jutter.smartcity.net.TeamsController

object TeamsRepository : BaseRepository<TeamsEntity>() {

    fun getTeamByUser(user: UserEntity) = executeTransaction {
        if (user.teamId != null) {
            getQuery<TeamsEntity>("id" eq user.teamId!!).firstOrNull()
        } else {
            null
        }
    }

    fun getTeamById(id: Int) = executeTransaction {
        getQuery<TeamsEntity>("id" eq id).firstOrNull()
    }

    fun getTeamByCapitan(id: Int) = executeTransaction {
        getQuery<TeamsEntity>("captainId" eq id).firstOrNull()
    }

    fun createTeam(name: String, code: String, phone: String) {
        executeTransaction {
            val user = UsersRepository.getUser(phone)

            val query = createSQLQuery(
                "INSERT INTO admin_hakaton_teams (name, is_searching, password, captain_id) VALUE ('${name}', 0, '${code}', '${user!!.id}')"
            )
            query.executeUpdate()
        }
        executeTransaction {
            val user = UsersRepository.getUser(phone)

            val dbEnt = getTeamByCapitan(user!!.id)
            user.teamId = dbEnt!!.id
            saveOrUpdate(user)
        }
    }

    fun joinTeam(id: Int, code: String, phone: String) = executeTransaction {
        val user = UsersRepository.getUser(phone)
        val dbEnt = getTeamById(id)
        if (code != dbEnt!!.password) error("Неправильный код")
        user!!.teamId = dbEnt.id
        saveOrUpdate(user)
    }
}
