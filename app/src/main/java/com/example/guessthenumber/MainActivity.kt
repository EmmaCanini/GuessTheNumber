package com.example.guessthenumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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


                    
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun appScreen() {
    Header()
//    Body()

        
    }


@Composable
fun Header() {
    var userInput by remember {mutableStateOf("")}
    var randomNumber by remember {mutableStateOf<Int?>(null)}
    var result by remember {mutableStateOf("")}


    Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "GUESS THE NUMBER",
            fontSize = 46.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.height(40.dp)
        )
        Spacer(modifier = Modifier.height(100.dp))
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Mystical Logo",
                    modifier = Modifier.fillMaxWidth().height(500.dp),

        )



        ModifiableTextField(userInput) {newValue -> userInput = newValue
        }
        Spacer(modifier = Modifier.height(16.dp))

        GuessButton(userInput, randomNumber) { comparisonResult -> result = comparisonResult
        }
        Spacer(modifier = Modifier.height(16.dp))

        GenerateNumberButton { generatedNumber -> randomNumber = generatedNumber
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = result)
    }

    }


//@Composable
//fun Body() {
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .padding(40.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            ModifiableTextField()
//            Spacer(modifier = Modifier.height(20.dp))
//            GuessButton()
//            Spacer(modifier = Modifier.height(450.dp))
////            GenerateNumberButton()
////        }}
//
//}



@Composable
fun ModifiableTextField(currentText: String, onTextChange: (String) -> Unit) {
    TextField(
        value = currentText,
        onValueChange = { newValue -> onTextChange(newValue) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = "type number...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}


@Composable
fun GuessButton(userInput: String, randomNumber: Int?, onResult: (String) -> Unit) {
    Button(onClick = {
        val userGuess = userInput.toIntOrNull()
        if (userGuess != null && randomNumber != null) {
            val comparisonResult = if (userGuess == randomNumber) {
                "Correct! The number was $randomNumber."
            } else {
                "Incorrect! The number was $randomNumber."
            }
            onResult(comparisonResult)
        } else {
            onResult("Please enter a valid number and generate a number first.")
        }
    }) {
        Text(
            text = stringResource(R.string.button_label)
        )
    }
}



@Composable
fun GenerateNumberButton(onNumberGenerated: (Int) -> Unit) {
    Button(onClick= {
        val randomNumber = (0..100).random()
        println(randomNumber)
        onNumberGenerated(randomNumber)

    }){
        Text(
            text= stringResource(R.string.button_label2)
        )
    }
}