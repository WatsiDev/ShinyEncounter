package com.watsidev.shinyencounter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.watsidev.shinyencounter.ui.theme.ShinyEncounterTheme
import com.watsidev.shinyencounter.viewmodel.CounterViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShinyEncounterTheme {
                CountApp()
            }
        }
    }
}

/**
 * Main app composable
 */
@Composable
fun CountApp() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        ShinyEncounterCounter(modifier = Modifier.padding(innerPadding))
    }
}

/**
 * Background composable
 */
@Composable
fun Background() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(listOf(Color(0xFF084787), Color(0xFFABD2FA))))
            .padding(bottom = 510.dp)
    ) {
        drawCircle(color = Color(0xFFABD2FA), size.minDimension)
    }
}

/**
 * The main screen of the app.
 *  @param modifier modifiers to set to this composable
 */
@Composable
fun ShinyEncounterCounter(
    modifier: Modifier = Modifier
) {
    Background()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .wrapContentSize(Alignment.TopStart)
    ) {
        IconSelect()
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Reset()
        ContentCounter()
        Buttons()
    }
}

/**
 * IconSelect composable
 *
 * @param viewModel the view model to use
 */
@Composable
fun IconSelect(viewModel: CounterViewModel = viewModel()) {
    IconButton(onClick = { viewModel.setExpanded(true) }) {
        Icon(Icons.Default.MoreVert, contentDescription = "Chose option", tint = Color.Black)
    }
    DropdownMenu(
        expanded = viewModel.expanded.collectAsState().value,
        onDismissRequest = { viewModel.setExpanded(false) }) {
        DropdownMenuItem(text = { Text(text = "1 avistamiento.") }, onClick = {
            viewModel.setPlus(1)
            viewModel.setExpanded(false)
        })
        DropdownMenuItem(text = { Text(text = "5 avistamientos.") }, onClick = {
            viewModel.setPlus(5)
            viewModel.setExpanded(false)
        })
    }
}


/**
 * ContentCounter composable
 *
 * @param viewModel the view model to use
 */
@Composable
fun ContentCounter(viewModel: CounterViewModel = viewModel()) {
    val count by viewModel.count.collectAsState()
    Image(
        painter = painterResource(R.drawable._29magikarpsprite),
        contentDescription = "SpritePokemon",
        modifier = Modifier
            .fillMaxWidth()
            .height(258.dp)
    )
    Text(
        text = count.toString(),
        fontSize = 48.sp,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold
    )
}

/**
 * Buttons increment and decrement composable
 *
 * @param viewModel the view model to use
 */
@Composable
fun Buttons(viewModel: CounterViewModel = viewModel()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { viewModel.decrement() },
            colors = ButtonDefaults.buttonColors(Color(0xFFDE688C))
        ) {
            Text(text = "➖")
        }
        Button(
            onClick = { viewModel.increment() },
            colors = ButtonDefaults.buttonColors(Color(0xFFDE688C))
        ) {
            Text(text = "➕")
        }
    }
}


/**
 * Reset count composable
 *
 * @param viewModel the view model to use
 */
@Composable
fun Reset(viewModel: CounterViewModel = viewModel()) {
    Button(
        onClick = { viewModel.reset() },
        colors = ButtonDefaults.buttonColors(Color(0xFFDE688C))
    ) {
        Text(text = "Reset")
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    ShinyEncounterTheme {
        ShinyEncounterCounter()
    }
}