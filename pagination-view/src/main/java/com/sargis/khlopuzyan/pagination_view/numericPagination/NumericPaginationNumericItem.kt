package com.sargis.khlopuzyan.pagination_view.numericPagination

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.NumericPageUiItem
import com.sargis.khlopuzyan.pagination_view.theme.NumericPaginationItemText

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun NumericPaginationNumericItem(
    modifier: Modifier = Modifier,
    numericPageItem: NumericPageUiItem,
    numericPageUiItemsSize: Int,
    //
    numericPaginationItemContainerWidth: Dp,
    numericPaginationItemContainerHeight: Dp,
    numericPaginationItemWidth: Dp,
    numericPaginationItemHeight: Dp,
    spaceBetweenPaginationItems: Dp,
    numericPaginationItemCornerRadius: Dp,
    numericPaginationItemBorderStroke: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        if (numericPageUiItemsSize > 1 && numericPageItem.uiPageIndex != 1) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationItems / 2))
        }

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
                    .border(
                        border = BorderStroke(
                            width = numericPaginationItemBorderStroke,
                            color = if (numericPageItem.isSelected) {
                                Color.Black
                            } else {
                                Color.Transparent
                            }
                        ),
                        shape = RoundedCornerShape(numericPaginationItemCornerRadius)
                    )
                    .clip(
                        shape = RoundedCornerShape(numericPaginationItemCornerRadius)
                    )
                    .clickable(
                        // Uncomment to disable ripple effect when clicking
//                        indication = null, interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (!numericPageItem.isSelected) {
                            pageClicked(numericPageItem.page)
                        }
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "${numericPageItem.page}",
                    style = NumericPaginationItemText,
                    color = Color.Black
                )
            }
        }

        if (numericPageUiItemsSize > 1 && numericPageItem.uiPageIndex != numericPageUiItemsSize) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationItems / 2))
        }

    }

}