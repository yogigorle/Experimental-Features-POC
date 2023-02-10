package com.example.experimentalfeaturespoc

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.toColorInt

class JetPackComposePlayGround : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            MaterialTheme {
                Surface(color = Color.White) {
                    Column {
                        DrawMaterialBtn()
                    }
                }
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    private fun DrawMaterialBtn() {
        //text view
        Text(
            text = "Phone pay style button in compose",
            style = TextStyle.Default,
            modifier = Modifier.padding(8.dp),
            color = Color.Green
        )

        val buttonColors = ButtonDefaults.buttonColors(
            Color("#6739B7".toColorInt()),
            contentColor = Color.White,
            )
            Column(Modifier.fillMaxWidth().absolutePadding(10.dp,20.dp,10.dp,10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                //material button
                Button(
                    onClick = { showToast("FASAK") },
                    shape = RoundedCornerShape(20.dp),
                    colors = buttonColors,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Click me ")
                }
            }


    }

    @Preview
    @Composable
    private fun DefaultPreview() {
        MaterialTheme {
            Column{
                DrawMaterialBtn()
            }
        }
    }

}

