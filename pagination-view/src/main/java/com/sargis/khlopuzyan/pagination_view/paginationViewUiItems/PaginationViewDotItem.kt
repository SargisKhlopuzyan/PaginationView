package com.sargis.khlopuzyan.pagination_view.paginationViewUiItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewItemResources
import com.sargis.khlopuzyan.pagination_view.theme.PaginationViewNumericItemText

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewDotItem(
    modifier: Modifier = Modifier,
    paginationViewItemResources: PaginationViewItemResources
) {
    Box(
        modifier = modifier
            .width(paginationViewItemResources.paginationViewItemContainerWidth)
            .height(paginationViewItemResources.paginationViewItemContainerHeight),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .width(paginationViewItemResources.paginationViewItemWidth)
                .height(paginationViewItemResources.paginationViewItemHeight)
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