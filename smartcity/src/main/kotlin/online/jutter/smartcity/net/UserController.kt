package online.jutter.smartcity.net

import online.jnetutter.smartcity.common.ext.createEmptyWrapperResponse
import online.jnetutter.smartcity.common.ext.createWrapperResponse
import online.jutter.smartcity.data.db.repositories.TeamsRepository
import online.jutter.smartcity.data.db.repositories.UsersRepository
import online.jutter.smartcity.data.models.CreateUserRequest
import online.jutter.smartcity.data.models.JoinTeamRequest
import online.jutter.smartcity.data.models.ProfileResponse
import online.jutter.smartcity.domain.GetUserResultUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/users"],
        produces = ["application/json; charset=utf-8"]
)
class UserController {

    @RequestMapping(
        value = ["get/{phone}"],
        method = [RequestMethod.GET]
    )
    fun all(@PathVariable("phone") phone: String) = createWrapperResponse {
        val user = UsersRepository.getUser(phone)
        ProfileResponse(
            user = user!!,
            team = TeamsRepository.getTeamByUser(user),
            result = GetUserResultUseCase().invoke(user),
        )
    }

    @RequestMapping(
        value = ["register"],
        method = [RequestMethod.POST]
    )
    fun register(@RequestBody cu: CreateUserRequest) = createEmptyWrapperResponse {
        UsersRepository.create(cu)
    }
}