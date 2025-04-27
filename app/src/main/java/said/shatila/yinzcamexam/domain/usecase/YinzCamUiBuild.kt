package said.shatila.yinzcamexam.domain.usecase

import said.shatila.yinzcamexam.model.local.Schedule
import said.shatila.yinzcamexam.model.remote.ScheduleDTO


fun interface YinzCamUiBuild {
    operator fun invoke(scheduleDTO: ScheduleDTO): Schedule
}
