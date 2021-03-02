package com.akiapps.petfiner

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akiapps.petfiner.data.Pet
import com.akiapps.petfiner.ui.composeutils.ComposeListText
import com.akiapps.petfiner.ui.composeutils.ComposeToolbar
import com.akiapps.petfiner.ui.composeutils.getToolbarModifier
import com.akiapps.petfiner.ui.theme.PetFinderTheme

class DetailsActivity : AppCompatActivity() {

    private val petDetails: Pet by lazy {
        intent.getParcelableExtra("Pet")!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InitComposers() }
    }

    @Composable
    fun InitComposers() {
        PetFinderTheme() {
            Column() {
                ComposeToolbar(Modifier.getToolbarModifier())
                Surface(
                    color = MaterialTheme.colors.background, modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    ComposeDogDetails()
                }
            }

        }
    }

    @Composable
    fun ComposeDogDetails() {
        Column() {
            Image(
                painter = painterResource(id = petDetails.petImage),
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            ComposeListText(text = petDetails.owner.name, fontSize = 21.sp, style = MaterialTheme.typography.h6,Modifier.align(Alignment.CenterHorizontally))
        }
    }


    @Preview(showBackground = true, name = "Layout Preview")
    @Composable
    fun DefaultPreview() {
        InitComposers()
    }
}