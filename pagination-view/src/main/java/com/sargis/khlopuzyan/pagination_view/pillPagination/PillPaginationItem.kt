package com.sargis.khlopuzyan.pagination_view.pillPagination

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.PillPageUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PillPaginationItem(
    modifier: Modifier = Modifier,
    pillPageUiItem: PillPageUiItem,
    pillPageUiItemsSize: Int,
    //
    pillPaginationItemContainerWidth: Dp,
    pillPaginationItemContainerHeight: Dp,
    pillPaginationItemWidth: Dp,
    pillPaginationItemHeight: Dp,
    spaceBetweenPaginationItems: Dp,
    pillPaginationItemCornerRadius: Dp,
    pillPaginationItemBorderStroke: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {

        if (pillPageUiItemsSize > 1 && pillPageUiItem.uiPageIndex != 1) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationItems / 2))
        }

        Box(
            modifier = modifier
                .width(pillPaginationItemContainerWidth)
                .height(pillPaginationItemContainerHeight),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .width(pillPaginationItemWidth)
                    .height(pillPaginationItemHeight)
                    .border(
                        border = BorderStroke(
                            width = pillPaginationItemBorderStroke,
                            color = Color.Black
                        ),
                        shape = RoundedCornerShape(pillPaginationItemCornerRadius)
                    )
                    .clip(
                        shape = RoundedCornerShape(pillPaginationItemCornerRadius)
                    )
                    .background(
                        if (pillPageUiItem.isSelected) {
                            Color.Black
                        } else {
                            Color.Transparent
                        }
                    )
                    .clickable(
                        // Uncomment to disable ripple effect when clicking
//                        indication = null, interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (!pillPageUiItem.isSelected) {
                            pageClicked(pillPageUiItem.page)
                        }
                    }
            )
        }

        if (pillPageUiItemsSize > 1 && pillPageUiItem.uiPageIndex != pillPageUiItemsSize) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationItems / 2))
        }

    }

}