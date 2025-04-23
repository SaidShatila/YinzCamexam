package said.shatila.yinzcamexam.domain.repository

import io.ktor.client.HttpClient
import kotlinx.coroutines.withContext
import said.shatila.yinzcamexam.data.remote.ScheduleDTO
import said.shatila.yinzcamexam.network.GenericResponse
import javax.inject.Inject

class YinzCamRepoImpl @Inject constructor(
    private val client: HttpClient,
    @CONtext
) : YinzCamRepository {
    override suspend fun fetchYinz(): GenericResponse<ScheduleDTO> {
        withContext(Dispatchers.IO) {
            runCatching {
                client.get<ScheduleDTO> {
                    url {
                        protocol = baseEndpoint.protocol           // URLProtocol.HTTPS
                        host       = baseEndpoint.host               // e.g. "api.myserver.com"
                        // if your BaseEndpoint has a `prefixPath`, include it here:
                        // encodedPath = "${baseEndpoint.prefixPath}/iOS/interviews/ScheduleExercise/schedule.json"
                        encodedPath = "/iOS/interviews/ScheduleExercise/schedule.json"
                    }
                }
            }.fold(
                onSuccess = { GenericResponse.Success(it) },
                onFailure = { GenericResponse.Error(it) }
            )
        }
    }
}