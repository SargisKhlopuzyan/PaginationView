package com.sargis.khlopuzyan.pagination_view.numericPagination

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination_view.PaginationState
import com.sargis.khlopuzyan.pagination_view.PaginationStyle
import com.sargis.khlopuzyan.pagination_view.PaginationViewUiDimens
import com.sargis.khlopuzyan.pagination_view.backwardOrForwardItem.BackwardOrForwardItemCompose

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationWithBackwardAndForward(

    paginationState: PaginationState,

    itemsSize: Int,
    hideViewPagerInOnePageMode: Boolean,
    paginationStyle: PaginationStyle,

    paginationViewUiDimens: PaginationViewUiDimens,

    onPageClicked: (pageNumber: Int) -> Unit

) {

    val isPaginationViewVisible =
        paginationState.pageUiItems.isNotEmpty() && !(hideViewPagerInOnePageMode && paginationState.pageUiItems.size == 1)

    if (isPaginationViewVisible) {

        val horizontalAlignment = if (paginationStyle == PaginationStyle.PACKED) {
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
                //
                backwardOrForwardItemContainerWidth = paginationViewUiDimens.backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = paginationViewUiDimens.backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = paginationViewUiDimens.backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = paginationViewUiDimens.backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationItem = paginationViewUiDimens.spaceBetweenBackwardOrForwardItemAndPaginationItem,
                backwardOrForwardItemCornerRadius = paginationViewUiDimens.backwardOrForwardItemCornerRadius
                //
            ) { page ->
                onPageClicked(page)
            }

            Pagination(
                modifier = Modifier.fillMaxHeight()
                    .run {
                        if (paginationStyle == PaginationStyle.PACKED) {
                            wrapContentWidth()
                        } else {
                            width(0.dp)
                            weight(1f)
                        }
                    },
                pageUiItems = paginationState.pageUiItems,
                //
                paginationItemContainerWidth = paginationViewUiDimens.paginationItemContainerWidth,
                paginationItemContainerHeight = paginationViewUiDimens.paginationItemContainerHeight,
                paginationItemWidth = paginationViewUiDimens.paginationItemWidth,
                paginationItemHeight = paginationViewUiDimens.paginationItemHeight,
                spaceBetweenPaginationItems = paginationViewUiDimens.spaceBetweenPaginationItems,
                paginationItemCornerRadius = paginationViewUiDimens.paginationItemCornerRadius,
                paginationItemBorderStroke = paginationViewUiDimens.paginationItemBorderStroke
                //
            ) { page ->
                onPageClicked(page)
            }

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = false,
                //
                backwardOrForwardItemContainerWidth = paginationViewUiDimens.backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = paginationViewUiDimens.backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = paginationViewUiDimens.backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = paginationViewUiDimens.backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationItem = paginationViewUiDimens.spaceBetweenBackwardOrForwardItemAndPaginationItem,
                backwardOrForwardItemCornerRadius = paginationViewUiDimens.backwardOrForwardItemCornerRadius
                //
            ) { page ->
                onPageClicked(page)
            }

        }
    }

}