package online.jnetutter.smartcity.common.ext

import online.jutter.roadmap.data.models.DataWrapper

/**
 * Этот метод занимается формированием
 * ответа сервера, и обработкой входных
 * данных запроса.
 *
 * @param block блок, содержащий всю
 *        логику обработки запроса.
 */
fun <T> createWrapperResponse(block: () -> T?) : DataWrapper<T> {
    return try {
        DataWrapper(
            true,
            message = "success",
            data = block()
        )
    } catch (ex: Exception) {
        DataWrapper(
            false,
            message = ex.message.toString(),
            data = null
        )
    }
}

/**
 * Создаем шаблонный ответ сервера
 * с обработкой ошибки.
 */
fun createEmptyWrapperResponse(block: () -> Unit) : DataWrapper<Int> {
    return try {
        block()
        DataWrapper(
            true,
            message = "success",
            data = 0
        )
    } catch (ex: Exception) {
        DataWrapper(
            false,
            message = ex.message.toString() + ex.localizedMessage.toString(),
            data = 0
        )
    }
}