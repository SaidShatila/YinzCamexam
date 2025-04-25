package said.shatila.yinzcamexam.domain.usecase

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import said.shatila.yinzcamexam.data.local.YINZ_CAM
import said.shatila.yinzcamexam.data.remote.ScheduleDTO
import said.shatila.yinzcamexam.domain.IODispatcher
import said.shatila.yinzcamexam.domain.repository.YinzCamRepository
import said.shatila.yinzcamexam.network.GenericResponse
import said.shatila.yinzcamexam.network.NetworkResponse
import javax.inject.Inject

class FetchYinzCamUseCase @Inject constructor(
    private val yinzCamRepository: YinzCamRepository,
    private val savedStateHandle: SavedStateHandle,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): GenericResponse<ScheduleDTO> {
        val job = CompletableDeferred<GenericResponse<ScheduleDTO>>()
        withContext(ioDispatcher) {
            val res = yinzCamRepository.fetchYinz()
            if (res is NetworkResponse.Success) {
//                savedStateHandle[YINZ_CAM] = res.data
                job.complete(NetworkResponse.Success(data = res.data))
            } else if (res is NetworkResponse.Failure) {
                job.complete(NetworkResponse.Failure(res.error, res.message))
            }
        }

        return job.await()
    }
}