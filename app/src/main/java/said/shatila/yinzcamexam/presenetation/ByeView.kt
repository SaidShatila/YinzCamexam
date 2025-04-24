package said.shatila.yinzcamexam.presenetation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import said.shatila.yinzcamexam.R
import said.shatila.yinzcamexam.ui.theme.BackgroundColor
import said.shatila.yinzcamexam.ui.theme.PrimaryTextColor

@Composable
fun ByeView(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundColor)
            .heightIn(min = 200.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.bye_capitalized),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = PrimaryTextColor
        )
    }
}


@Preview
@Composable
private fun ByePreview() {
    ByeView()
}