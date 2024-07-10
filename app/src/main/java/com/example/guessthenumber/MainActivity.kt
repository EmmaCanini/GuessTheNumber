package com.example.guessthenumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.guessthenumber.ui.theme.GuessTheNumberTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessTheNumberTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    appScreen()
                    GuessButton()
                    ModifiableTextField()
                    
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Composable
@Preview(showBackground = true)
fun appScreen() {
    Header()
    Body()
    GuessButton()
        
    }


@Composable
fun Header() {
    val headerModifier = Modifier.fillMaxSize()
    Box(modifier = headerModifier) {
        Text(text = "GUESSTHENUMBER",
            fontSize = 46.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

    }
}



@Composable
fun Body() {

}


@Composable
fun ModifiableTextField() {
    var currentText by remember {
        mutableStateOf("")
    }
    TextField(value = currentText,
        onValueChange = { newValue -> currentText = newValue }
    )
}


@Composable
fun GuessButton() {
    Button(onClick= {
        println("Guess submitted")
    }){
        Text(
            text= stringResource(R.string.button_label)
        )
    }
}