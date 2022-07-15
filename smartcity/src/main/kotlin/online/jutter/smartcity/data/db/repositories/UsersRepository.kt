package online.jutter.smartcity.data.db.repositories

import online.jutter.smartcity.common.ext.getQuery
import online.jutter.smartcity.data.db.BaseRepository
import online.jutter.smartcity.data.db.ent.UserEntity
import online.jutter.smartcity.data.db.eq
import online.jutter.smartcity.data.models.CreateUserRequest

object UsersRepository : BaseRepository<UserEntity>() {

    fun getUser(phone: String) = executeTransaction {
        getQuery<UserEntity>("phone" eq phone).firstOrNull()
    }

    fun getUserById(id: Int) = executeTransaction {
        getQuery<UserEntity>("id" eq id).firstOrNull()
    }

    fun create(cu: CreateUserRequest) = executeTransaction {
        val query = createSQLQuery(
            "INSERT INTO admin_hakaton_users (firstname, lastname, phone, city, avatar) VALUE ('${cu.name}', '${cu.lastname}', '${cu.phone}', '${cu.city}', '${cu.avatar}')"
        )
        query.executeUpdate()
    }
}
