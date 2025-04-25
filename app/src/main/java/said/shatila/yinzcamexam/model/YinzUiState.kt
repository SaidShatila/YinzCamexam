package said.shatila.yinzcamexam.model

import said.shatila.yinzcamexam.data.local.Schedule

sealed interface YinzUiState {
    val data: Schedule

    data class Loading(
        override val data: Schedule,
    ) : YinzUiState

    data class Success(
        override val data: Schedule,
    ) : YinzUiState

    data class Error(
        override val data: Schedule,
        val error: String
    ) : YinzUiState
}