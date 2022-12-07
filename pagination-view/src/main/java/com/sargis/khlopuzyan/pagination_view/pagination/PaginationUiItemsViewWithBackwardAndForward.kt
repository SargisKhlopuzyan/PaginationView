package com.sargis.khlopuzyan.pagination_view.pagination

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination_view.backwardOrForwardItem.BackwardOrForwardItemCompose
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewDimens
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationItemsViewWithBackwardAndForward(
    paginationViewInfo: PaginationViewInfo,
    paginationViewUiItems: List<PaginationViewUiItem>,
    paginationViewDimens: PaginationViewDimens,
    onPageClicked: (pageNumber: Int) -> Unit
) {

    val isPaginationViewVisible =
        paginationViewUiItems.isNotEmpty() && !(paginationViewInfo.hideViewPagerInOnePageMode && paginationViewUiItems.size == 1)

    if (isPaginationViewVisible) {

        val horizontalAlignment =
            if (paginationViewInfo.paginationViewStyle == PaginationViewStyle.PACKED) {
                Arrangement.Center
            } else {
                Arrangement.SpaceBetween
            }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = horizontalAlignment
        ) {
            BackwardOrForwardItemCompose(
                paginationViewInfo = paginationViewInfo,
                isBackwardIcon = true,
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
                        if (paginationViewInfo.paginationViewStyle == PaginationViewStyle.PACKED) {
                            wrapContentWidth()
                        } else {
                            width(0.dp)
                            weight(1f)
                        }
                    },
                paginationViewUiItems = paginationViewUiItems,
                selectedPage = paginationViewInfo.selectedPage,
                animateOnPressEvent = paginationViewInfo.animateOnPressEvent,
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
                paginationViewInfo = paginationViewInfo,
                isBackwardIcon = false,
                paginationViewBackwardOrForwardItemDimens = paginationViewDimens.paginationViewBackwardOrForwardItemDimens
            ) { page ->
                onPageClicked(page)
            }
        }
    }
}