package online.jutter.smartcity.data.models

import online.jutter.smartcity.data.db.ent.TeamsEntity
import online.jutter.smartcity.data.db.ent.UserEntity

data class ProfileResponse(
   val user: UserEntity,
   val team: TeamsEntity?,
   val result: List<Int>
)
