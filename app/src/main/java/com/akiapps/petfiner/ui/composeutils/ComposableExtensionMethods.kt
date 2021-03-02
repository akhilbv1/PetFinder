package com.akiapps.petfiner.ui.composeutils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akiapps.petfiner.R
import com.akiapps.petfiner.ui.theme.TextColorDarkTheme
import com.akiapps.petfiner.ui.theme.TextColorLightTheme

@Preview
@Composable
fun Preview() {
    ComposeToolbar()
}


@Composable
fun ComposeToolbar(modifier: Modifier = Modifier) {
    TopAppBar(modifier = modifier) {
        Text(
            text = "Pet Finder",
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically)
                .padding(10.dp),
            color = Color.White,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ComposeListText(
    text: String,
    fontSize: TextUnit,
    style: TextStyle,
    modifier: Modifier = Modifier,
    textColor:Color = Color.White,
    ) {
    Text(
        text,
        modifier = modifier,
        style = style,
        maxLines = 2,
        color = textColor,
        fontSize = fontSize,
        fontFamily = FontFamily.Monospace
    )
}