package com.akiapps.petfiner.ui.composeutils

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


fun Modifier.getToolbarModifier(): Modifier {
    return this
        .fillMaxWidth()
        .height(51.dp)
}
