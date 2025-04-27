package said.shatila.yinzcamexam.data.repository

import said.shatila.yinzcamexam.model.remote.ScheduleDTO
import said.shatila.yinzcamexam.network.GenericResponse

interface YinzCamRepository {

    suspend fun fetchYinz() : GenericResponse<ScheduleDTO>
}