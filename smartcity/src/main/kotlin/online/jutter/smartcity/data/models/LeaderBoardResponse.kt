package online.jutter.smartcity.data.models

import online.jutter.smartcity.data.db.ent.*

data class LeaderBoardResponse(
   val competition: CompetitionEntity,
   val list: MutableList<PositionResponse>
)

data class PositionResponse(
   val rank: Int,
   val name: String,
)
