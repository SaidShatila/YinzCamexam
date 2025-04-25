package said.shatila.yinzcamexam.domain.usecase

import said.shatila.yinzcamexam.data.local.Schedule
import said.shatila.yinzcamexam.data.remote.ScheduleDTO
import said.shatila.yinzcamexam.extension.toSectionUIModels

class YinzCamUiBuild {

    operator fun invoke(scheduleDTO: ScheduleDTO): Schedule {
        return scheduleDTO.toSectionUIModels()
    }
}