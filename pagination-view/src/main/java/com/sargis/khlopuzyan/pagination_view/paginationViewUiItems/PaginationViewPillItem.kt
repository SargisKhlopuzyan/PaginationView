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
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewItemResources
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewPillUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewPillItem(
    modifier: Modifier = Modifier,
    pillPageUiItem: PaginationViewPillUiItem,
    isSelected: Boolean,
    animateOnPressEvent: Boolean,
    paginationViewItemResources: PaginationViewItemResources,
    pageClicked: (page: Int) -> Unit
) {
    Box(
        modifier = modifier
            .width(paginationViewItemResources.paginationViewItemContainerWidth)
            .height(paginationViewItemResources.paginationViewItemContainerHeight),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = modifier
            .width(paginationViewItemResources.paginationViewItemWidth)
            .height(paginationViewItemResources.paginationViewItemHeight)
            .border(
                border = BorderStroke(
                    width = paginationViewItemResources.paginationViewItemBorderStroke,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(paginationViewItemResources.paginationViewItemCornerRadius)
            )
            .clip(
                shape = RoundedCornerShape(paginationViewItemResources.paginationViewItemCornerRadius)
            )
            .background(
                if (isSelected) {
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
                if (!isSelected) {
                    pageClicked(pillPageUiItem.page)
                }
            })
    }

}