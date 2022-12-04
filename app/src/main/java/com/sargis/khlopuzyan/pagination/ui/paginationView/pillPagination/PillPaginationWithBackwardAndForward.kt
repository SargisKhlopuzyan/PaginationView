package com.sargis.khlopuzyan.pagination.ui.paginationView.pillPagination

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination.ui.paginationView.PaginationState
import com.sargis.khlopuzyan.pagination.ui.paginationView.PaginationStyle
import com.sargis.khlopuzyan.pagination.ui.paginationView.backwardOrForwardItem.BackwardOrForwardItemCompose

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PillPaginationWithBackwardAndForward(

    paginationState: PaginationState,
    //
    itemsSize: Int,
    paginationStyle: PaginationStyle,
    //
    pillPaginationItemContainerWidth: Dp,
    pillPaginationItemContainerHeight: Dp,
    pillPaginationItemWidth: Dp,
    pillPaginationItemHeight: Dp,
    spaceBetweenPillPaginationItems: Dp,
    //
    backwardOrForwardItemContainerWidth: Dp,
    backwardOrForwardItemContainerHeight: Dp,
    backwardOrForwardItemWidth: Dp,
    backwardOrForwardItemHeight: Dp,
    spaceBetweenBackwardOrForwardItemAndPillPaginationItem: Dp,
    pillPaginationItemCornerRadius: Dp,
    pillPaginationItemBorderStroke: Dp,
    backwardOrForwardItemCornerRadius: Dp,
    //
    onPageClicked: (pageNumber: Int) -> Unit,
) {
    val horizontalAlignment =
        if (paginationStyle == PaginationStyle.PACKED) {
            Arrangement.Center
        } else {
            Arrangement.SpaceBetween
        }
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = horizontalAlignment
    ) {

        if (paginationState.pageUiItems.isNotEmpty()) {

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = true,
                //
                backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationItem = spaceBetweenBackwardOrForwardItemAndPillPaginationItem,
                backwardOrForwardItemCornerRadius = backwardOrForwardItemCornerRadius
                //
            ) { page ->
                onPageClicked(page)
            }

            PillPagination(

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
                pillPaginationItemContainerWidth = pillPaginationItemContainerWidth,
                pillPaginationItemContainerHeight = pillPaginationItemContainerHeight,
                pillPaginationItemWidth = pillPaginationItemWidth,
                pillPaginationItemHeight = pillPaginationItemHeight,
                spaceBetweenPillPaginationItems = spaceBetweenPillPaginationItems,
                pillPaginationItemCornerRadius = pillPaginationItemCornerRadius,
                pillPaginationItemBorderStroke = pillPaginationItemBorderStroke
                //
            ) { page ->
                onPageClicked(page)
            }

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = false,
                //
                backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationItem = spaceBetweenBackwardOrForwardItemAndPillPaginationItem,
                backwardOrForwardItemCornerRadius = backwardOrForwardItemCornerRadius
                //
            ) { page ->
                onPageClicked(page)
            }
        }
    }
}