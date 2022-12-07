package com.sargis.khlopuzyan.pagination_view.paginationViewUiItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewItemDimens
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewNumericUiItem
import com.sargis.khlopuzyan.pagination_view.theme.PaginationViewNumericItemText

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewNumericItem(
    modifier: Modifier = Modifier,
    paginationViewNumericUiItem: PaginationViewNumericUiItem,
    isSelected: Boolean,
    animateOnPressEvent: Boolean,
    paginationViewItemDimens: PaginationViewItemDimens,
    pageClicked: (page: Int) -> Unit
) {
    Box(
        modifier = modifier
            .height(paginationViewItemDimens.paginationViewItemContainerHeight)
            .width(paginationViewItemDimens.paginationViewItemContainerWidth),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .height(paginationViewItemDimens.paginationViewItemHeight)
                .width(paginationViewItemDimens.paginationViewItemWidth)
                .border(
                    border = BorderStroke(
                        width = paginationViewItemDimens.paginationViewItemBorderStroke,
                        color = if (isSelected) {
                            Color.Black
                        } else {
                            Color.Transparent
                        }
                    ),
                    shape = RoundedCornerShape(paginationViewItemDimens.paginationViewItemCornerRadius)
                )
                .clip(
                    shape = RoundedCornerShape(paginationViewItemDimens.paginationViewItemCornerRadius)
                )
                .clickable(
                    indication = if (!animateOnPressEvent) null else LocalIndication.current,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) {
                    if (!isSelected) {
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
}