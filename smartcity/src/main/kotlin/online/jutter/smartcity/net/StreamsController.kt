package online.jutter.smartcity.net

import online.jnetutter.smartcity.common.ext.createWrapperResponse
import online.jutter.smartcity.data.db.repositories.StreamsRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
        value = ["api/streams"],
        produces = ["application/json; charset=utf-8"]
)
class StreamsController {

    @RequestMapping(
        value = ["all"],
        method = [RequestMethod.GET]
    )
    fun all() = createWrapperResponse {
        StreamsRepository.getAll()
    }
}