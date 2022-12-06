package com.sargis.khlopuzyan.pagination_view.pagination

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination_view.data.PaginationState
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewUiDimens
import com.sargis.khlopuzyan.pagination_view.backwardOrForwardItem.BackwardOrForwardItemCompose

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationWithBackwardAndForward(

    paginationState: PaginationState,

    itemsSize: Int,
    hideViewPagerInOnePageMode: Boolean,
    animateOnPressEvent: Boolean,
    paginationViewStyle: PaginationViewStyle,

    paginationViewUiDimens: PaginationViewUiDimens,

    onPageClicked: (pageNumber: Int) -> Unit

) {

    val isPaginationViewVisible =
        paginationState.paginationViewUiItems.isNotEmpty() && !(hideViewPagerInOnePageMode && paginationState.paginationViewUiItems.size == 1)

    if (isPaginationViewVisible) {

        val horizontalAlignment = if (paginationViewStyle == PaginationViewStyle.PACKED) {
            Arrangement.Center
        } else {
            Arrangement.SpaceBetween
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = horizontalAlignment
        ) {

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = true,
                isClickAnimationEnabled = animateOnPressEvent,
                //
                backwardOrForwardItemContainerWidth = paginationViewUiDimens.backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = paginationViewUiDimens.backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = paginationViewUiDimens.backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = paginationViewUiDimens.backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationViewItem = paginationViewUiDimens.spaceBetweenBackwardOrForwardItemAndPaginationViewItem,
                backwardOrForwardItemCornerRadius = paginationViewUiDimens.backwardOrForwardItemCornerRadius
                //
            ) { page ->
                onPageClicked(page)
            }

            Pagination(
                modifier = Modifier.fillMaxHeight()
                    .run {
                        if (paginationViewStyle == PaginationViewStyle.PACKED) {
                            wrapContentWidth()
                        } else {
                            width(0.dp)
                            weight(1f)
                        }
                    },
                paginationViewUiItems = paginationState.paginationViewUiItems,
                animateOnPressEvent = animateOnPressEvent,
                //
                paginationViewItemContainerWidth = paginationViewUiDimens.paginationViewItemContainerWidth,
                paginationViewItemContainerHeight = paginationViewUiDimens.paginationViewItemContainerHeight,
                paginationViewItemWidth = paginationViewUiDimens.paginationViewItemWidth,
                paginationViewItemHeight = paginationViewUiDimens.paginationViewItemHeight,
                spaceBetweenPaginationViewItems = paginationViewUiDimens.spaceBetweenPaginationViewItems,
                paginationViewItemCornerRadius = paginationViewUiDimens.paginationViewItemCornerRadius,
                paginationViewItemBorderStroke = paginationViewUiDimens.paginationViewItemBorderStroke
                //
            ) { page ->
                onPageClicked(page)
            }

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = false,
                isClickAnimationEnabled = animateOnPressEvent,
                //
                backwardOrForwardItemContainerWidth = paginationViewUiDimens.backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = paginationViewUiDimens.backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = paginationViewUiDimens.backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = paginationViewUiDimens.backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationViewItem = paginationViewUiDimens.spaceBetweenBackwardOrForwardItemAndPaginationViewItem,
                backwardOrForwardItemCornerRadius = paginationViewUiDimens.backwardOrForwardItemCornerRadius
                //
            ) { page ->
                onPageClicked(page)
            }

        }
    }

}