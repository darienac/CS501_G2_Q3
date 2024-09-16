package com.example.cs501_g2_q3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.cs501_g2_q3.ui.theme.CS501_G2_Q3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CS501_G2_Q3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppLayout(modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppLayout(modifier: Modifier = Modifier) {
    var output by remember {mutableStateOf("")}
    Column(modifier=modifier.fillMaxWidth()) {
        CalculatorOutput(output) {value: String ->
            output = value
        }
    }
}

@Composable
fun CalculatorOutput(value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(textAlign = TextAlign.Right),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CS501_G2_Q3Theme {
        AppLayout()
    }
}