package said.shatila.yinzcamexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import said.shatila.yinzcamexam.presenetation.TopAppBarView
import said.shatila.yinzcamexam.presenetation.YinzCamMainScreen
import said.shatila.yinzcamexam.ui.theme.BackgroundColor
import said.shatila.yinzcamexam.ui.theme.YinzCamexamTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YinzCamexamTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBarView() }) { innerPadding ->
                    YinzCamMainScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(BackgroundColor)
                    )
                }
            }
        }
    }
}
