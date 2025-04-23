package said.shatila.yinzcamexam.domain.repository

import said.shatila.yinzcamexam.data.remote.ScheduleDTO
import said.shatila.yinzcamexam.network.GenericResponse

interface YinzCamRepository {

    suspend fun fetchYinz() : GenericResponse<ScheduleDTO>
}