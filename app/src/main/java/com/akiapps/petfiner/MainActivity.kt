package com.akiapps.petfiner

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akiapps.petfiner.data.Pet
import com.akiapps.petfiner.data.getPetsList
import com.akiapps.petfiner.ui.composeutils.ComposeListText
import com.akiapps.petfiner.ui.theme.PetFinderTheme
import com.akiapps.petfiner.ui.theme.TextColorDarkTheme
import com.akiapps.petfiner.ui.theme.TextColorLightTheme

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitComposers()
        }
    }

    private fun toggleTheme() {
        val uiMode = resources.configuration.uiMode
        if ((uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }


    @Composable
    fun InitComposers() {
        PetFinderTheme() {
            Column() {
                //  ComposeToolbar(Modifier.getToolbarModifier())
                val bgColor by  animateColorAsState(targetValue = MaterialTheme.colors.background)
                Surface(
                    color = bgColor, modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Column() {
                        ComposeHeader()
                        ShowListOfData(data = getPetsList()) {
                            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                            intent.putExtra("Pet", getPetsList()[it])
                            startActivity(intent)
                        }
                    }
                }
            }

        }
    }

    @Composable
    fun ShowListOfData(data: Array<Pet>, onClick: (index: Int) -> Unit) {
        LazyColumn {
            items(items = data.toList(), null, itemContent = { item: Pet ->
                val index = data.indexOf(item)
                NewStory(dataItem = item, index = index, onClick = onClick)
            })
        }
    }

    @Composable
    fun NewStory(dataItem: Pet, index: Int, onClick: (index: Int) -> Unit) {
        Card(
            elevation = 10.dp,
            shape = RoundedCornerShape(5.dp),
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier
                .clickable {
                    onClick(index)
                }
                .padding(15.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                RowItemScenery(dataItem = dataItem) {
                }
            }
        }
    }

    @Composable
    fun RowItemScenery(dataItem: Pet, onCheckedChangeListener: (Boolean) -> Unit) {
        Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.Start) {
            Image(
                painter = painterResource(id = dataItem.petImage), contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)), contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(5.dp))
            ComposeListText(
                text = dataItem.petName,
                fontSize = 16.sp,
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            ComposeListText(
                text = "${dataItem.petYear} years | ${dataItem.gender}",
                fontSize = 12.sp,
                style = typography.body1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    @Composable
    fun ComposeHeader() {
        val uiMode = resources.configuration.uiMode
        val theme = remember(calculation = {
            mutableStateOf((uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES)
        })
        val themeTextColor = if (isSystemInDarkTheme()) TextColorDarkTheme else TextColorLightTheme

        Spacer(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(10.dp)
        )
        Row(Modifier.padding(PaddingValues(start = 15.dp))) {
            ComposeListText(
                text = "Hello Akhil!",
                fontSize = 22.sp,
                style = typography.h6,
                textColor = themeTextColor,
                modifier = Modifier
                    .wrapContentSize(
                        Alignment.TopStart
                    )
                    .weight(1f, true)
                    .align(Alignment.Top)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_light),
                contentDescription = null,
                modifier = Modifier
                    .weight(.2f, true)
                    .align(Alignment.CenterVertically)
                    .size(24.dp, 24.dp)
                    .clickable {
                        theme.value =
                            (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
                        toggleTheme()
                    }
            )

        }
    }


    @Preview(showBackground = true, name = "Layout Preview")
    @Composable
    fun DefaultPreview() {
        InitComposers()
    }

}
