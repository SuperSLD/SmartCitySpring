package online.jutter.smartcity.net

import online.jnetutter.smartcity.common.ext.createWrapperResponse
import online.jutter.smartcity.data.db.repositories.NewsRepository
import online.jutter.smartcity.data.db.repositories.TeamsCompetitionsRepository
import online.jutter.smartcity.data.db.repositories.TeamsRepository
import online.jutter.smartcity.domain.GetLeaderBoardUseCase
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
        value = ["api/leaderboard"],
        produces = ["application/json; charset=utf-8"]
)
class LeaderBoardController {

    @RequestMapping(
        value = ["all"],
        method = [RequestMethod.GET]
    )
    fun all() = createWrapperResponse {
        GetLeaderBoardUseCase().invoke()
    }

    @RequestMapping(
        value = ["test"],
        method = [RequestMethod.GET]
    )
    fun test() = createWrapperResponse {
        TeamsCompetitionsRepository.getAll()
    }
}