package online.jutter.smartcity.net

import online.jnetutter.smartcity.common.ext.createEmptyWrapperResponse
import online.jnetutter.smartcity.common.ext.createWrapperResponse
import online.jutter.smartcity.data.db.repositories.CompetitionsRepository
import online.jutter.smartcity.data.db.repositories.NewsRepository
import online.jutter.smartcity.data.db.repositories.TeamsCompetitionsRepository
import online.jutter.smartcity.domain.GetScheduleUseCase
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
        value = ["api/competitions"],
        produces = ["application/json; charset=utf-8"]
)
class CompetitionsController {

    @RequestMapping(
        value = ["all"],
        method = [RequestMethod.GET]
    )
    fun all() = createWrapperResponse {
        GetScheduleUseCase().invoke()
    }

    @RequestMapping(
        value = ["join/{phone}/{id}"],
        method = [RequestMethod.GET]
    )
    fun join(
        @PathVariable("phone") phone: String,
        @PathVariable("id") id: Int,
    ) = createEmptyWrapperResponse {
        TeamsCompetitionsRepository.join(phone, id)
    }
}