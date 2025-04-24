package said.shatila.yinzcamexam.domain.usecase

import said.shatila.yinzcamexam.data.local.Schedule

class YinzCamUiBuild {


    operator fun invoke() : Schedule{
        return Schedule(
            uniqueId = TODO(),
            team = TODO(),
            defaultGameId = TODO(),
            gameSection = TODO()
        )
    }
}