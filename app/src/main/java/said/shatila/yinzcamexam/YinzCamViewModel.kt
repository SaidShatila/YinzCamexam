package said.shatila.yinzcamexam

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import said.shatila.yinzcamexam.domain.usecase.YinzCamUseCases
import javax.inject.Inject

@HiltViewModel
class YinzCamViewModel @Inject constructor(private val yinzCamUseCases: YinzCamUseCases) :
    ViewModel() {


}