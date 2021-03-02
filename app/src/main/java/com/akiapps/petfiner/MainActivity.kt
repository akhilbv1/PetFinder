package com.akiapps.petfiner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
import com.akiapps.petfiner.ui.composeutils.ComposeToolbar
import com.akiapps.petfiner.ui.composeutils.getToolbarModifier
import com.akiapps.petfiner.ui.theme.PetFinderTheme

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitComposers()
        }
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
                    ShowListOfData(data = getPetsList()) {
                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        intent.putExtra("Pet", getPetsList()[it])
                        startActivity(intent)
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


    @Preview(showBackground = true, name = "Layout Preview")
    @Composable
    fun DefaultPreview() {
        InitComposers()
    }

}
