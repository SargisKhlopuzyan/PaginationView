package com.sargis.khlopuzyan.pagination_view.pagination

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination_view.backwardOrForwardItem.BackwardOrForwardItemCompose
import com.sargis.khlopuzyan.pagination_view.data.PaginationState
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewDimens
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import com.sargis.khlopuzyan.pagination_view.util.initPaginationViewUiItems

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationItemsViewWithBackwardAndForward(
    paginationViewInfo: PaginationViewInfo,
    paginationViewDimens: PaginationViewDimens,
    onPageClicked: (pageNumber: Int) -> Unit
) {

    var paginationState by remember {
        mutableStateOf(
            PaginationState(
                selectedPage = paginationViewInfo.selectedPage,
                paginationViewUiItems = paginationViewInfo.paginationViewUiItems
            )
        )
    }

    if (paginationState.paginationViewUiItems.size != paginationViewInfo.paginationViewUiItems.size) {
        paginationState = PaginationState(
            selectedPage = paginationViewInfo.selectedPage,
            paginationViewUiItems = paginationViewInfo.paginationViewUiItems
        )
    }


    val isPaginationViewVisible =
        paginationState.paginationViewUiItems.isNotEmpty() && !(paginationViewInfo.hideViewPagerInOnePageMode && paginationState.paginationViewUiItems.size == 1)

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
                selectedPage = paginationState.selectedPage,
                pagesSize = paginationViewInfo.pagesSize,
                isBackwardIcon = true,
                isClickAnimationEnabled = paginationViewInfo.animateOnPressEvent,
                paginationViewBackwardOrForwardItemDimens = paginationViewDimens.paginationViewBackwardOrForwardItemDimens
            ) { page ->

                paginationState = PaginationState(
                    selectedPage = page,
                    paginationViewUiItems = initPaginationViewUiItems(
                        pagesSize = paginationViewInfo.pagesSize,
                        alwaysShowNumber = paginationViewInfo.alwaysShowNumber,
                        paginationViewUiItemsMaxCount = paginationViewInfo.paginationViewUiItemsMaxCount,
                        selectedPage = page
                    )
                )

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
                paginationViewUiItems = paginationState.paginationViewUiItems,
                animateOnPressEvent = paginationViewInfo.animateOnPressEvent,
                paginationViewDimens = paginationViewDimens
            ) { page ->

                paginationState = PaginationState(
                    selectedPage = page,
                    paginationViewUiItems = initPaginationViewUiItems(
                        pagesSize = paginationViewInfo.pagesSize,
                        alwaysShowNumber = paginationViewInfo.alwaysShowNumber,
                        paginationViewUiItemsMaxCount = paginationViewInfo.paginationViewUiItemsMaxCount,
                        selectedPage = page
                    )
                )

                onPageClicked(page)
            }

            Spacer(
                modifier = Modifier.width(
                    paginationViewDimens.spaceBetweenBackwardOrForwardItemAndPaginationViewItem
                )
            )

            BackwardOrForwardItemCompose(
                selectedPage = paginationState.selectedPage,
                pagesSize = paginationViewInfo.pagesSize,
                isBackwardIcon = false,
                isClickAnimationEnabled = paginationViewInfo.animateOnPressEvent,
                paginationViewBackwardOrForwardItemDimens = paginationViewDimens.paginationViewBackwardOrForwardItemDimens
            ) { page ->

                paginationState = PaginationState(
                    selectedPage = page,
                    paginationViewUiItems = initPaginationViewUiItems(
                        pagesSize = paginationViewInfo.pagesSize,
                        alwaysShowNumber = paginationViewInfo.alwaysShowNumber,
                        paginationViewUiItemsMaxCount = paginationViewInfo.paginationViewUiItemsMaxCount,
                        selectedPage = page
                    )
                )

                onPageClicked(page)
            }
        }
    }
}