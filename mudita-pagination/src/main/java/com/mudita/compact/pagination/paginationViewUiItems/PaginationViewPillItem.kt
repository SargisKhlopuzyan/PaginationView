package com.mudita.compact.pagination.paginationViewUiItems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.mudita.compact.pagination.data.ItemResources
import com.mudita.compact.pagination.data.PaginationViewPillUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewPillItem(
    modifier: Modifier = Modifier,
    pillPageUiItem: PaginationViewPillUiItem,
    isSelected: Boolean,
    animateOnPressEvent: Boolean,
    paginationViewItemResources: ItemResources,
    pageClicked: (page: Int) -> Unit
) {
    Box(
        modifier = modifier
            .width(paginationViewItemResources.itemContainerWidth)
            .height(paginationViewItemResources.itemContainerHeight),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = modifier
            .width(paginationViewItemResources.itemWidth)
            .height(paginationViewItemResources.itemHeight)
            .border(
                border = BorderStroke(
                    width = paginationViewItemResources.itemBorderStroke,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(paginationViewItemResources.itemCornerRadius)
            )
            .clip(
                shape = RoundedCornerShape(paginationViewItemResources.itemCornerRadius)
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