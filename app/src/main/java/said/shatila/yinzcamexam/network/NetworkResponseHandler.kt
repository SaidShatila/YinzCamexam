package said.shatila.yinzcamexam.network

import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import said.shatila.yinzcamexam.R
import java.io.IOException
import java.net.UnknownHostException


suspend inline fun <reified T, reified E> networkResponseOf(
    crossinline httpCallFunction: suspend () -> HttpResponse,
): NetworkResponse<T, E> {
    return try {
        val response = httpCallFunction()
        if (response.status.value in NetworkResponse.successCodeRange) {
            return NetworkResponse.Success(data = response.body() ?: Unit as T)
        }
        NetworkResponse.Failure(response.body() as E, null)
    } catch (ex: Exception) {
        NetworkResponse.Failure(null as? E, ex.getThrowableMsg())
    }
}



fun Exception.getThrowableMsg(): Int {
    return when (this) {
        is UnknownHostException -> R.string.request_timeout_error
        is SocketTimeoutException -> R.string.request_timeout_error
        is ConnectTimeoutException -> R.string.no_internet_connection
        is IOException -> R.string.no_internet_connection
        else -> R.string.default_error_message
    }
}