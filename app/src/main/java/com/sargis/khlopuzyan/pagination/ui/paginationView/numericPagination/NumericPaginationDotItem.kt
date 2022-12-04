package com.sargis.khlopuzyan.pagination.ui.paginationView.numericPagination

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination.ui.theme.NumericPaginationItemText

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun NumericPaginationDotItem(
    modifier: Modifier = Modifier,
    //
    numericPaginationItemContainerWidth: Dp,
    numericPaginationItemContainerHeight: Dp,
    numericPaginationItemWidth: Dp,
    numericPaginationItemHeight: Dp,
    spaceBetweenNumericPaginationItems: Dp,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        Spacer(modifier = Modifier.width(spaceBetweenNumericPaginationItems / 2))

        Box(
            modifier = modifier
                .height(numericPaginationItemContainerHeight)
                .width(numericPaginationItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(numericPaginationItemHeight)
                    .width(numericPaginationItemWidth)
            ) {
                Text(
                    modifier = Modifier
                        .matchParentSize()
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter),
                    text = "...",
                    textAlign = TextAlign.Center,
                    style = NumericPaginationItemText,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.width(spaceBetweenNumericPaginationItems / 2))
    }

}