package said.shatila.yinzcamexam.domain.usecase

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import said.shatila.yinzcamexam.model.remote.ScheduleDTO
import said.shatila.yinzcamexam.utils.IODispatcher
import said.shatila.yinzcamexam.data.repository.YinzCamRepository
import said.shatila.yinzcamexam.network.GenericResponse
import said.shatila.yinzcamexam.network.NetworkResponse
import javax.inject.Inject

class FetchYinzCamUseCase @Inject constructor(
    private val yinzCamRepository: YinzCamRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): GenericResponse<ScheduleDTO> {
        val job = CompletableDeferred<GenericResponse<ScheduleDTO>>()
        withContext(ioDispatcher) {
            val res = yinzCamRepository.fetchYinz()
            if (res is NetworkResponse.Success) {
                job.complete(NetworkResponse.Success(data = res.data))
            } else if (res is NetworkResponse.Failure) {
                job.complete(NetworkResponse.Failure(res.error, res.message))
            }
        }

        return job.await()
    }
}