package said.shatila.yinzcamexam

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import said.shatila.yinzcamexam.model.local.Schedule
import said.shatila.yinzcamexam.model.local.YINZ_CAM
import said.shatila.yinzcamexam.model.remote.ScheduleDTO
import said.shatila.yinzcamexam.utils.MainDispatcher
import said.shatila.yinzcamexam.domain.usecase.YinzCamUseCases
import said.shatila.yinzcamexam.data.extension.toSectionUIModels
import said.shatila.yinzcamexam.model.YinzCamUiState
import said.shatila.yinzcamexam.network.NetworkResponse
import javax.inject.Inject

@HiltViewModel
class YinzCamViewModel @Inject constructor(
    private val yinzCamUseCases: YinzCamUseCases,
    private val savedStateHandle: SavedStateHandle,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var scheduleDTO: ScheduleDTO? = savedStateHandle[YINZ_CAM]
        get() {
            return savedStateHandle.get<String>(YINZ_CAM)?.let { Json.decodeFromString(it) }
        }
        set(value) {
            savedStateHandle[YINZ_CAM] = value.let { Json.encodeToString(it) }
            field = value
        }

    private val _yinzCamUIState = MutableStateFlow<YinzCamUiState>(
        YinzCamUiState.Loading(Schedule())
    )
    val yinzCamUIState: StateFlow<YinzCamUiState> =
        _yinzCamUIState.onStart<YinzCamUiState> { fetchYinzCam() }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = YinzCamUiState.Loading(Schedule())
        )


    private fun fetchYinzCam() {
        viewModelScope.launch {
            val res = yinzCamUseCases.fetchYinzCamUseCase()
            withContext(mainDispatcher) {
                when (res) {
                    is NetworkResponse.Success -> {

                        _yinzCamUIState.update {
                            scheduleDTO = res.data
                            YinzCamUiState.Success(res.data.toSectionUIModels())
                        }
                    }

                    is NetworkResponse.Failure -> {
                        _yinzCamUIState.update {
                            YinzCamUiState.Error(
                                Schedule(), "Unknown"
                            )
                        }
                    }
                }
            }
        }
    }
}