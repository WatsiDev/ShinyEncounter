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
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.watsidev.shinyencounter.ui.theme.ShinyEncounterTheme
import kotlin.math.exp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShinyEncounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShinyEncounterCounter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

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

@Composable
fun ShinyEncounterCounter(modifier: Modifier = Modifier) {
    var count = remember {
        mutableIntStateOf(0)
    }
    var number = remember {
        mutableStateOf(0)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var plus by remember {
        mutableIntStateOf(1)
    }
    Background()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .wrapContentSize(Alignment.TopStart)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Chose option", tint = Color.Black)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text(text = "1 avistamiento.") }, onClick = {
                plus = 1
                expanded = false
            })
            DropdownMenuItem(text = { Text(text = "5 avistamientos.") }, onClick = {
                plus = 5
                expanded = false
            })
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(R.drawable._29magikarpsprite),
            contentDescription = "SpritePokemon",
            modifier = Modifier
                .fillMaxWidth()
                .height(258.dp)
        )
        Text(
            text = count.intValue.toString(),
            fontSize = 48.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            Arrangement.Center
        ) {
            Button(onClick = {
                if (plus != 5) {
                    if (count.intValue != 0) {
                        count.value--
                    }
                } else if (count.value > 0) {
                    count.value = count.value - 5
                } else if (count.value < 0) {
                    count.value = 0
                }
            }, colors = ButtonDefaults.buttonColors(Color(0xFFDE688C))) {
//                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "menos")
                Text(text = "➖")
            }
            Spacer(modifier = Modifier.padding(32.dp))
            Button(onClick = {
                if (plus != 5) {
                    count.value++
                } else {
                    count.value = count.value + 5
                }
            }, colors = ButtonDefaults.buttonColors(Color(0xFFDE688C))) {
//                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Add")
                Text(text = "➕")
            }
        }
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