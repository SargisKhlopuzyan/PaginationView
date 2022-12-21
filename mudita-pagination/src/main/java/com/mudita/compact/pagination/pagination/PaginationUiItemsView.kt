package com.mudita.compact.pagination.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mudita.compact.pagination.data.PaginationViewDotUiItem
import com.mudita.compact.pagination.data.PaginationViewNumericUiItem
import com.mudita.compact.pagination.data.PaginationViewPillUiItem
import com.mudita.compact.pagination.data.PaginationViewResources
import com.mudita.compact.pagination.data.PaginationViewUiItem
import com.mudita.compact.pagination.paginationViewUiItems.PaginationViewDotItem
import com.mudita.compact.pagination.paginationViewUiItems.PaginationViewNumericItem
import com.mudita.compact.pagination.paginationViewUiItems.PaginationViewPillItem

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
                        paginationViewItemResources = paginationViewResources.numericItemResources,
                        paginationViewNumericItemTextStyle = paginationViewResources.paginationViewNumericItemTextStyle
                    ) { page ->
                        pageClicked(page)
                    }
                }
                is PaginationViewDotUiItem -> {
                    PaginationViewDotItem(paginationViewItemResources = paginationViewResources.dotItemResources)
                }
                is PaginationViewPillUiItem -> {
                    PaginationViewPillItem(
                        pillPageUiItem = paginationViewUiItem,
                        isSelected = paginationViewUiItem.page == selectedPage,
                        animateOnPressEvent = animateOnPressEvent,
                        paginationViewItemResources = paginationViewResources.pillItemResources
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