package com.sargis.khlopuzyan.pagination_view.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sargis.khlopuzyan.pagination_view.data.*
import com.sargis.khlopuzyan.pagination_view.paginationViewUiItems.PaginationViewDotItem
import com.sargis.khlopuzyan.pagination_view.paginationViewUiItems.PaginationViewNumericItem
import com.sargis.khlopuzyan.pagination_view.paginationViewUiItems.PaginationViewPillItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PaginationItemsView(
    modifier: Modifier = Modifier,
    paginationViewUiItems: List<PaginationViewUiItem>,
    selectedPage: Int,
    animateOnPressEvent: Boolean,
    paginationViewResources: PaginationViewResources,
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        (paginationViewUiItems.indices).forEach {

            if (paginationViewUiItems.size > 1 && paginationViewUiItems[it].paginationViewUiItemIndex != 1) {
                Spacer(modifier = Modifier.width(paginationViewResources.spaceBetweenPaginationViewItems / 2))
            }

            when (val paginationViewUiItem: PaginationViewUiItem = paginationViewUiItems[it]) {
                is PaginationViewNumericUiItem -> {
                    PaginationViewNumericItem(
                        paginationViewNumericUiItem = paginationViewUiItem,
                        isSelected = paginationViewUiItem.page == selectedPage,
                        animateOnPressEvent = animateOnPressEvent,
                        paginationViewItemResources = paginationViewResources.paginationViewNumericItemResources
                    ) { page ->
                        pageClicked(page)
                    }
                }
                is PaginationViewDotUiItem -> {
                    PaginationViewDotItem(paginationViewItemResources = paginationViewResources.paginationViewDotItemResources)
                }
                is PaginationViewPillUiItem -> {
                    PaginationViewPillItem(
                        pillPageUiItem = paginationViewUiItem,
                        isSelected = paginationViewUiItem.page == selectedPage,
                        animateOnPressEvent = animateOnPressEvent,
                        paginationViewItemResources = paginationViewResources.paginationViewPillItemResources
                    ) { page ->
                        pageClicked(page)
                    }
                }
            }

            if (paginationViewUiItems.size > 1 && paginationViewUiItems[it].paginationViewUiItemIndex != paginationViewUiItems.size) {
                Spacer(modifier = Modifier.width(paginationViewResources.spaceBetweenPaginationViewItems / 2))
            }
        }
    }
}