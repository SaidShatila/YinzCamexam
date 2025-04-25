package said.shatila.yinzcamexam.domain.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import said.shatila.yinzcamexam.data.remote.ScheduleDTO
import said.shatila.yinzcamexam.domain.DomainConstants.BASE_URL
import said.shatila.yinzcamexam.domain.IODispatcher
import said.shatila.yinzcamexam.network.GenericResponse
import said.shatila.yinzcamexam.network.networkResponseOf
import javax.inject.Inject

class YinzCamRepoImpl @Inject constructor(
    private val client: HttpClient,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : YinzCamRepository {
    override suspend fun fetchYinz(): GenericResponse<ScheduleDTO> {

        val builder = HttpRequestBuilder().apply {
            url {
                protocol = URLProtocol.HTTP
                host = BASE_URL
                encodedPath = "iOS/interviews/ScheduleExercise/schedule.json"
            }
            method = HttpMethod.Get
        }

        return withContext(ioDispatcher) {
            networkResponseOf {
                client.request(builder)
            }
        }
    }
}
