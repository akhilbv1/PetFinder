package com.akiapps.petfiner

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akiapps.petfiner.ui.theme.PetFinderTheme

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitComposers()
        }
    }

    @Composable
    fun InitComposers() {
        PetFinderTheme {
            Surface(
                color = Color.White, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                PhotographerCard()
            }
        }
    }

    @Composable
    fun PhotographerCard() {
        Row(modifier = Modifier.wrapContentSize(Alignment.TopCenter).padding(10.dp)) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                // Image goes here
            }
            Column(modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)) {
                Text("Alfred Sisley", fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("3 minutes ago", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
    

    @Preview(showBackground = true, name = "Layout Preview")
    @Composable
    fun DefaultPreview() {
        InitComposers()
    }
}