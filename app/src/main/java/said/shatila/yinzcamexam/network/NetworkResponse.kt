package said.shatila.yinzcamexam.network

typealias GenericResponse<T> = NetworkResponse<T, T>

sealed interface NetworkResponse<out T, out E> {
    data class Success<T>(val data: T) : NetworkResponse<T, Nothing>
    data class Failure<E>(val error: E?, val message: Int?) : NetworkResponse<Nothing, E>
    companion object {
        val successCodeRange = 200..299
    }
}