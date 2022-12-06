package com.sargis.khlopuzyan.pagination_view.paginationViewUiItems

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.theme.PaginationViewNumericItemText

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewDotItem(
    modifier: Modifier = Modifier,
    //
    paginationViewDotItemContainerWidth: Dp,
    paginationViewDotItemContainerHeight: Dp,
    paginationViewDotItemWidth: Dp,
    paginationViewDotItemHeight: Dp,
    spaceBetweenPaginationViewItems: Dp,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        Spacer(modifier = Modifier.width(spaceBetweenPaginationViewItems / 2))

        Box(
            modifier = modifier
                .height(paginationViewDotItemContainerHeight)
                .width(paginationViewDotItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(paginationViewDotItemHeight)
                    .width(paginationViewDotItemWidth)
            ) {
                Text(
                    modifier = Modifier
                        .matchParentSize()
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter),
                    text = "...",
                    textAlign = TextAlign.Center,
                    style = PaginationViewNumericItemText,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.width(spaceBetweenPaginationViewItems / 2))
    }

}