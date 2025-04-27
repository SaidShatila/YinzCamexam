package said.shatila.yinzcamexam.model

import said.shatila.yinzcamexam.model.local.Schedule

sealed interface YinzCamUiState {
    val data: Schedule

    data class Loading(
        override val data: Schedule,
    ) : YinzCamUiState

    data class Success(
        override val data: Schedule,
    ) : YinzCamUiState

    data class Error(
        override val data: Schedule,
        val error: String
    ) : YinzCamUiState
}