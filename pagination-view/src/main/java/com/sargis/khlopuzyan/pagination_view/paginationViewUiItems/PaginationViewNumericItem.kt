package com.sargis.khlopuzyan.pagination_view.paginationViewUiItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewNumericUiItem
import com.sargis.khlopuzyan.pagination_view.theme.PaginationViewNumericItemText

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewNumericItem(
    modifier: Modifier = Modifier,
    paginationViewNumericUiItem: PaginationViewNumericUiItem,
    paginationViewNumericUiItemsSize: Int,
    animateOnPressEvent: Boolean,
    //
    paginationViewNumericItemContainerWidth: Dp,
    paginationViewNumericItemContainerHeight: Dp,
    paginationViewNumericItemWidth: Dp,
    paginationViewNumericItemHeight: Dp,
    spaceBetweenPaginationViewItems: Dp,
    paginationViewNumericItemCornerRadius: Dp,
    paginationViewNumericItemBorderStroke: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        if (paginationViewNumericUiItemsSize > 1 && paginationViewNumericUiItem.uiPageIndex != 1) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationViewItems / 2))
        }

        Box(
            modifier = modifier
                .height(paginationViewNumericItemContainerHeight)
                .width(paginationViewNumericItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(paginationViewNumericItemHeight)
                    .width(paginationViewNumericItemWidth)
                    .border(
                        border = BorderStroke(
                            width = paginationViewNumericItemBorderStroke,
                            color = if (paginationViewNumericUiItem.isSelected) {
                                Color.Black
                            } else {
                                Color.Transparent
                            }
                        ),
                        shape = RoundedCornerShape(paginationViewNumericItemCornerRadius)
                    )
                    .clip(
                        shape = RoundedCornerShape(paginationViewNumericItemCornerRadius)
                    )
                    .clickable(
                        indication = if (!animateOnPressEvent) null else LocalIndication.current,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (!paginationViewNumericUiItem.isSelected) {
                            pageClicked(paginationViewNumericUiItem.page)
                        }
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "${paginationViewNumericUiItem.page}",
                    style = PaginationViewNumericItemText,
                    color = Color.Black
                )
            }
        }

        if (paginationViewNumericUiItemsSize > 1 && paginationViewNumericUiItem.uiPageIndex != paginationViewNumericUiItemsSize) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationViewItems / 2))
        }

    }

}