package com.mudita.compact.pagination.paginationViewUiItems

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.mudita.compact.pagination.data.ItemResources
import com.mudita.compact.pagination.data.PaginationViewNumericUiItem


/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationViewNumericItem(
    modifier: Modifier = Modifier,
    paginationViewNumericUiItem: PaginationViewNumericUiItem,
    isSelected: Boolean,
    animateOnPressEvent: Boolean,
    paginationViewItemResources: ItemResources,
    paginationViewNumericItemTextStyle: TextStyle,
    pageClicked: (page: Int) -> Unit
) {
    Box(
        modifier = modifier
            .height(paginationViewItemResources.itemContainerHeight)
            .width(paginationViewItemResources.itemContainerWidth),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .height(paginationViewItemResources.itemHeight)
                .width(paginationViewItemResources.itemWidth)
                .border(
                    border = BorderStroke(
                        width = paginationViewItemResources.itemBorderStroke,
                        color = if (isSelected) Color.Black else Color.Transparent
                    ),
                    shape = RoundedCornerShape(paginationViewItemResources.itemCornerRadius)
                )
                .clip(
                    shape = RoundedCornerShape(paginationViewItemResources.itemCornerRadius)
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
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = paginationViewNumericItemTextStyle
            )
        }
    }
}