package com.sargis.khlopuzyan.pagination_view.paginationViewUiItems

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewPillUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewPillItem(
    modifier: Modifier = Modifier,
    pillPageUiItem: PaginationViewPillUiItem,
    pillPageUiItemsSize: Int,
    animateOnPressEvent: Boolean,
    //
    paginationViewPillItemContainerWidth: Dp,
    paginationViewPillItemContainerHeight: Dp,
    paginationViewPillItemWidth: Dp,
    paginationViewPillItemHeight: Dp,
    spaceBetweenPaginationViewItems: Dp,
    paginationViewPillItemCornerRadius: Dp,
    paginationViewPillItemBorderStroke: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {

        if (pillPageUiItemsSize > 1 && pillPageUiItem.uiPageIndex != 1) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationViewItems / 2))
        }

        Box(
            modifier = modifier
                .width(paginationViewPillItemContainerWidth)
                .height(paginationViewPillItemContainerHeight), contentAlignment = Alignment.Center
        ) {
            Box(modifier = modifier
                .width(paginationViewPillItemWidth)
                .height(paginationViewPillItemHeight)
                .border(
                    border = BorderStroke(
                        width = paginationViewPillItemBorderStroke, color = Color.Black
                    ), shape = RoundedCornerShape(paginationViewPillItemCornerRadius)
                )
                .clip(
                    shape = RoundedCornerShape(paginationViewPillItemCornerRadius)
                )
                .background(
                    if (pillPageUiItem.isSelected) {
                        Color.Black
                    } else {
                        Color.Transparent
                    }
                )
                .clickable(indication = if (!animateOnPressEvent) null else LocalIndication.current,
                    interactionSource = remember { MutableInteractionSource() }) {
                    if (!pillPageUiItem.isSelected) {
                        pageClicked(pillPageUiItem.page)
                    }
                })
        }

        if (pillPageUiItemsSize > 1 && pillPageUiItem.uiPageIndex != pillPageUiItemsSize) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationViewItems / 2))
        }

    }

}