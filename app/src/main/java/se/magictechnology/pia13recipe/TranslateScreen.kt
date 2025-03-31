package se.magictechnology.pia13recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TranslateScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Text("Hello")

        Text(stringResource(R.string.welcome), fontSize = 25.sp)

        Text(stringResource(R.string.funtext))

        Text(stringResource(R.string.hellouser, "Banan"))

        Image(painter = painterResource(R.drawable.frog), contentDescription = "frog")

        Text("This is not translated")
    }
}

@Preview(showBackground = true, locale = "en")
@Composable
fun TranslateScreenPreviewEN() {
    TranslateScreen()
}

@Preview(showBackground = true, locale = "es")
@Composable
fun TranslateScreenPreviewES() {
    TranslateScreen()
}

