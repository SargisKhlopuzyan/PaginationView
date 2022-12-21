package com.mudita.compact.pagination.paginationViewUiItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.mudita.compact.pagination.data.ItemResources
import com.mudita.compact.pagination.theme.PaginationViewNumericItemText

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewDotItem(
    modifier: Modifier = Modifier,
    paginationViewItemResources: ItemResources
) {
    Box(
        modifier = modifier
            .width(paginationViewItemResources.itemContainerWidth)
            .height(paginationViewItemResources.itemContainerHeight),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .width(paginationViewItemResources.itemWidth)
                .height(paginationViewItemResources.itemHeight)
        ) {
            Text(
                modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.BottomCenter),
                text = "...",
                textAlign = TextAlign.Center,
                style = PaginationViewNumericItemText,
                color = Color.Black
            )
        }
    }
}