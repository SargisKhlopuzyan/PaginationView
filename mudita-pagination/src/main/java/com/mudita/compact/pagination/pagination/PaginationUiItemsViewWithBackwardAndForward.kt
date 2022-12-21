package com.mudita.compact.pagination.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mudita.compact.pagination.backwardOrForwardItem.BackwardOrForwardItemCompose
import com.mudita.compact.pagination.data.PaginationViewInfo
import com.mudita.compact.pagination.data.PaginationViewResources
import com.mudita.compact.pagination.data.PaginationViewStyle
import com.mudita.compact.pagination.data.PaginationViewUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationItemsViewWithBackwardAndForward(
    paginationViewInfo: PaginationViewInfo,
    paginationViewUiItems: List<PaginationViewUiItem>,
    paginationViewResources: PaginationViewResources,
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
                backwardOrForwardItemResources = paginationViewResources.backwardOrForwardItemResources
            ) { page ->
                onPageClicked(page)
            }

            Spacer(
                modifier = Modifier.width(
                    paginationViewResources.spaceBetweenBackwardOrForwardItemAndPaginationViewItem
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
                paginationViewResources = paginationViewResources
            ) { page ->
                onPageClicked(page)
            }

            Spacer(
                modifier = Modifier.width(
                    paginationViewResources.spaceBetweenBackwardOrForwardItemAndPaginationViewItem
                )
            )

            BackwardOrForwardItemCompose(
                paginationViewInfo = paginationViewInfo,
                isBackwardIcon = false,
                backwardOrForwardItemResources = paginationViewResources.backwardOrForwardItemResources
            ) { page ->
                onPageClicked(page)
            }
        }
    }
}