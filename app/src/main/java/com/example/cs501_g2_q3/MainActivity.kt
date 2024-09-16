package com.example.cs501_g2_q3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cs501_g2_q3.ui.theme.CS501_G2_Q3Theme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CS501_G2_Q3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppLayout(modifier=Modifier.displayCutoutPadding().padding(innerPadding))
                }
            }
        }
    }
}

enum class Operation {
    NONE, ADD, MULTIPLY, SUBTRACT, DIVIDE, SQRT
}

@Composable
fun AppLayout(modifier: Modifier = Modifier) {
    var output by remember {mutableStateOf("")}
    var argNum by remember {mutableStateOf(0)}
    var operation by remember {mutableStateOf(Operation.NONE)}

    fun calculate(arg1: Double, arg2: Double): Number {
        var out: Double
        when (operation) {
            Operation.NONE -> out = arg1
            Operation.ADD -> out = arg1 + arg2
            Operation.MULTIPLY -> out = arg1 * arg2
            Operation.SUBTRACT -> out = arg1 - arg2
            Operation.DIVIDE -> out = arg1 / arg2
            Operation.SQRT -> out = sqrt(arg1)
        }
        operation = Operation.NONE
        return out
    }

    Column(modifier=modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 8.dp)) {
        CalculatorOutput(output) {value: String ->
            output = value
        }
        Spacer(modifier=Modifier.weight(4f))
        ButtonRow() {
            CalcButton("1", 1f) {output += "1"}
            CalcButton("2", 1f) {output += "2"}
            CalcButton("3", 1f) {output += "3"}
            CalcButton("+", 1f) {}
            CalcButton("*", 1f) {}
        }
        ButtonRow() {
            CalcButton("4", 1f) {output += "4"}
            CalcButton("5", 1f) {output += "5"}
            CalcButton("6", 1f) {output += "6"}
            CalcButton("-", 1f) {}
            CalcButton("\\", 1f) {}
        }
        ButtonRow() {
            CalcButton("7", 1f) {output += "7"}
            CalcButton("8", 1f) {output += "8"}
            CalcButton("9", 1f) {output += "9"}
            CalcButton("sqrt", 2f) {

            }
        }
        ButtonRow() {
            CalcButton("0", 1f) {output += "0"}
            CalcButton(".", 1f) {output += "."}
            CalcButton("C", 1f) {
                output = ""
            }
            CalcButton("=", 2f) {}
        }
    }
}

@Composable
fun ColumnScope.ButtonRow(content: @Composable RowScope.() -> Unit) {
    Row(modifier = Modifier.weight(1f).padding(PaddingValues(4.dp, 8.dp, 4.dp, 0.dp)), content=content)
}

@Composable
fun RowScope.CalcButton(label: String, width: Float, onClick: () -> Unit) {
    FilledTonalButton(
        onClick=onClick,
        modifier=Modifier.weight(width).padding(4.dp, 0.dp, 4.dp, 0.dp).fillMaxSize(),
        shape=RoundedCornerShape(16.dp)
    ) {
        Text(label, fontSize=24.sp)
    }
}

@Composable
fun CalculatorOutput(value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(textAlign = TextAlign.Right, fontSize=36.sp, fontFamily= FontFamily.Monospace),
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