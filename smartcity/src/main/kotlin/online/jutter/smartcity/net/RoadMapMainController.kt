package online.jutter.smartcity.net

import online.jnetutter.smartcity.common.ext.createEmptyWrapperResponse
import online.jnetutter.smartcity.common.ext.createWrapperResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/main"],
        produces = ["application/json; charset=utf-8"]
)
class RoadMapMainController {

    @RequestMapping(
        value = ["gledit/{id}"],
        method = [RequestMethod.GET]
    )
    fun glEdit(@PathVariable id: String) = createEmptyWrapperResponse {

    }

    @RequestMapping(
        value = ["map/{id}"],
        method = [RequestMethod.GET]
    )
    fun getMap(@PathVariable id: String) = createWrapperResponse {

    }

    @RequestMapping(
        value = ["rooms/{id}"],
        method = [RequestMethod.GET]
    )
    fun getObjectsMapRooms(@PathVariable id: String) = createWrapperResponse {

    }

}