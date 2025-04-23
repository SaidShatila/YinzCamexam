package said.shatila.yinzcamexam.presenetation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun YinzCamMainScreen(
    modifier: Modifier = Modifier,
//    viewModel: YinzCamViewModel = hiltViewModel()
) {
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    YinzCamBodyScreen(
        modifier = modifier,
//        uiState = uiState
    )
}

    @Composable
    private fun YinzCamBodyScreen(
        modifier: Modifier = Modifier,
//        uiState: GameScheduleUIState,
    ) {
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            item {

            }
        }
    }
}