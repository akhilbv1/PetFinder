package com.akiapps.petfiner

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akiapps.petfiner.data.Pet
import com.akiapps.petfiner.data.getPetsList
import com.akiapps.petfiner.ui.composeutils.ComposeListText
import com.akiapps.petfiner.ui.theme.PetFinderTheme
import com.akiapps.petfiner.ui.theme.Purple500
import com.akiapps.petfiner.ui.theme.TextColorDarkTheme
import com.akiapps.petfiner.ui.theme.TextColorLightTheme

class DetailsActivity : AppCompatActivity() {

    private val petDetails: Pet by lazy {
      //  getPetsList()[0]
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
        val themeTextColor = if (isSystemInDarkTheme()) TextColorDarkTheme else TextColorLightTheme
        Column() {
            Image(
                painter = painterResource(id = petDetails.petImage),
                contentDescription = null,
                Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            ComposeListText(
                text = petDetails.petName,
                fontSize = 18.sp,
                style = MaterialTheme.typography.h6,
                textColor = themeTextColor,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            ComposeOwnerDetails()
        }

    }

    @Composable
    private fun ComposeOwnerDetails() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                color = Purple500,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 15.dp, bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                ComposeListText(
                    text = "Owner Details",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.h6,
                    textColor = androidx.compose.ui.graphics.Color.White,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Divider(
                color = Purple500,
                modifier = Modifier
                    .height(50.dp)
                    .width(4.dp),
                thickness = 15.dp
            )
            Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Purple500,
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Column() {
                    ComposeListText(
                        text = petDetails.owner.name,
                        fontSize = 21.sp,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    ComposeListText(
                        text = "${petDetails.distance} kms away",
                        fontSize = 21.sp,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 15.dp)
                    )
                }
            }
        }

    }


    @Preview()
    @Composable
    fun DefaultPreview() {
        InitComposers()
    }
}