package said.shatila.yinzcamexam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import said.shatila.yinzcamexam.domain.usecase.YinzCamUseCases
import javax.inject.Inject

@HiltViewModel
class YinzCamViewModel @Inject constructor(private val yinzCamUseCases: YinzCamUseCases) :
    ViewModel() {
//    private val _yinzCamUIState =
//        MutableStateFlow(
//            yinzCamUseCases.getDefaultState(::handleIntent, null)
//        )
//
//    val yinzCamUIState =
//        _yinzCamUIState.onStart {
//            fetchYinzCam()
//        }.stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000L),
//            yinzCamUseCases.getDefaultState(::handleIntent, null)
//        )


    private fun fetchYinzCam() {
        viewModelScope.launch {

        }
    }
}