package said.shatila.yinzcamexam.domain.usecase

import said.shatila.yinzcamexam.data.local.Schedule
import said.shatila.yinzcamexam.data.remote.ScheduleDTO


fun interface YinzCamUiBuild {
    operator fun invoke(scheduleDTO: ScheduleDTO): Schedule
}
