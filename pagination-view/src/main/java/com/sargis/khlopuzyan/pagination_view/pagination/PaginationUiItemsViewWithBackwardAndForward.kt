package com.sargis.khlopuzyan.pagination_view.pagination

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination_view.backwardOrForwardItem.BackwardOrForwardItemCompose
import com.sargis.khlopuzyan.pagination_view.data.PaginationState
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewDimens
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationItemsViewWithBackwardAndForward(
    paginationState: PaginationState,
    itemsSize: Int,
    hideViewPagerInOnePageMode: Boolean,
    animateOnPressEvent: Boolean,
    paginationViewStyle: PaginationViewStyle,
    paginationViewDimens: PaginationViewDimens,
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
                paginationViewBackwardOrForwardItemDimens = paginationViewDimens.paginationViewBackwardOrForwardItemDimens
            ) { page ->
                onPageClicked(page)
            }

            Spacer(
                modifier = Modifier.width(
                    paginationViewDimens.spaceBetweenBackwardOrForwardItemAndPaginationViewItem
                )
            )

            PaginationItemsView(
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
                paginationViewDimens = paginationViewDimens
            ) { page ->
                onPageClicked(page)
            }

            Spacer(
                modifier = Modifier.width(
                    paginationViewDimens.spaceBetweenBackwardOrForwardItemAndPaginationViewItem
                )
            )

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = false,
                isClickAnimationEnabled = animateOnPressEvent,
                paginationViewBackwardOrForwardItemDimens = paginationViewDimens.paginationViewBackwardOrForwardItemDimens
            ) { page ->
                onPageClicked(page)
            }
        }
    }
}