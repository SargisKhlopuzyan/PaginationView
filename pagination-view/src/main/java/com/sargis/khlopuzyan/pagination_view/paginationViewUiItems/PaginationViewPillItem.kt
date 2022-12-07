package com.sargis.khlopuzyan.pagination_view.paginationViewUiItems

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewItemDimens
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewPillUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewPillItem(
    modifier: Modifier = Modifier,
    pillPageUiItem: PaginationViewPillUiItem,
    animateOnPressEvent: Boolean,
    paginationViewItemDimens: PaginationViewItemDimens,
    pageClicked: (page: Int) -> Unit
) {
    Box(
        modifier = modifier
            .width(paginationViewItemDimens.paginationViewItemContainerWidth)
            .height(paginationViewItemDimens.paginationViewItemContainerHeight),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = modifier
            .width(paginationViewItemDimens.paginationViewItemWidth)
            .height(paginationViewItemDimens.paginationViewItemHeight)
            .border(
                border = BorderStroke(
                    width = paginationViewItemDimens.paginationViewItemBorderStroke,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(paginationViewItemDimens.paginationViewItemCornerRadius)
            )
            .clip(
                shape = RoundedCornerShape(paginationViewItemDimens.paginationViewItemCornerRadius)
            )
            .background(
                if (pillPageUiItem.isSelected) {
                    Color.Black
                } else {
                    Color.Transparent
                }
            )
            .clickable(
                indication = if (!animateOnPressEvent) null else LocalIndication.current,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                if (!pillPageUiItem.isSelected) {
                    pageClicked(pillPageUiItem.page)
                }
            })
    }

}