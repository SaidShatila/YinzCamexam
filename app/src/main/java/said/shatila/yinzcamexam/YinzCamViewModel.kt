package said.shatila.yinzcamexam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import said.shatila.yinzcamexam.data.local.Schedule
import said.shatila.yinzcamexam.domain.usecase.YinzCamUseCases
import said.shatila.yinzcamexam.extension.toSectionUIModels
import said.shatila.yinzcamexam.model.YinzCamUiState
import said.shatila.yinzcamexam.network.NetworkResponse
import javax.inject.Inject

@HiltViewModel
class YinzCamViewModel @Inject constructor(private val yinzCamUseCases: YinzCamUseCases) :
    ViewModel() {
    private val _yinzCamUIState =
        MutableStateFlow<YinzCamUiState>(
            YinzCamUiState.Loading(Schedule("1"))
        )
    val yinzCamUIState: StateFlow<YinzCamUiState> =
        _yinzCamUIState
            .onStart<YinzCamUiState> { fetchYinzCam() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = YinzCamUiState.Loading(Schedule("1"))
            )


    private fun fetchYinzCam() {
        viewModelScope.launch {
            val res = yinzCamUseCases.fetchYinzCamUseCase()
            when (res) {
                is NetworkResponse.Success -> {
                    _yinzCamUIState.update {
                        YinzCamUiState.Success(res.data.toSectionUIModels())
                    }
                }

                is NetworkResponse.Failure -> {
                    _yinzCamUIState.update {
                        YinzCamUiState.Error(
                            Schedule("1"), "Unknown"
                        )
                    }
                }
            }
        }
    }
}