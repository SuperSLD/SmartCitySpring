package online.jutter.smartcity.net

import online.jnetutter.smartcity.common.ext.createEmptyWrapperResponse
import online.jnetutter.smartcity.common.ext.createWrapperResponse
import online.jutter.smartcity.data.db.repositories.NewsRepository
import online.jutter.smartcity.data.db.repositories.TeamsRepository
import online.jutter.smartcity.data.db.repositories.UsersRepository
import online.jutter.smartcity.data.models.CreateTeamRequest
import online.jutter.smartcity.data.models.JoinTeamRequest
import online.jutter.smartcity.data.models.ProfileResponse
import online.jutter.smartcity.domain.GetUserResultUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/teams"],
        produces = ["application/json; charset=utf-8"]
)
class TeamsController {

    @RequestMapping(
        value = ["all"],
        method = [RequestMethod.GET]
    )
    fun all() = createWrapperResponse {
        TeamsRepository.getAll()
    }

    @RequestMapping(
        value = ["create"],
        method = [RequestMethod.POST]
    )
    fun create(@RequestBody ct: CreateTeamRequest) = createEmptyWrapperResponse {
        TeamsRepository.createTeam(ct.name, ct.code, ct.phone)
    }

    @RequestMapping(
        value = ["join"],
        method = [RequestMethod.POST]
    )
    fun join(@RequestBody jt: JoinTeamRequest) = createEmptyWrapperResponse {
        TeamsRepository.joinTeam(jt.id, jt.code, jt.phone)
    }
}