package com.sargis.khlopuzyan.pagination.ui.paginationView.pillPagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination.ui.paginationView.PillPageUiItem
import com.sargis.khlopuzyan.pagination.ui.paginationView.PageUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun PillPagination(
    modifier: Modifier = Modifier,
    pageUiItems: List<PageUiItem>,
    //
    pillPaginationItemContainerWidth: Dp,
    pillPaginationItemContainerHeight: Dp,
    pillPaginationItemWidth: Dp,
    pillPaginationItemHeight: Dp,
    spaceBetweenPillPaginationItems: Dp,
    pillPaginationItemCornerRadius: Dp,
    pillPaginationItemBorderStroke: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        val count = pageUiItems.size

        (0 until count).forEach {
            val pageUiItem: PageUiItem? = pageUiItems.getOrNull(it)
            if (pageUiItem is PillPageUiItem) {
                PillPaginationItem(
                    pillPageUiItem = pageUiItem,
                    pillPageUiItemsSize = pageUiItems.size,
                    //
                    pillPaginationItemContainerWidth = pillPaginationItemContainerWidth,
                    pillPaginationItemContainerHeight = pillPaginationItemContainerHeight,
                    pillPaginationItemWidth = pillPaginationItemWidth,
                    pillPaginationItemHeight = pillPaginationItemHeight,
                    spaceBetweenPillPaginationItems = spaceBetweenPillPaginationItems,
                    pillPaginationItemCornerRadius = pillPaginationItemCornerRadius,
                    pillPaginationItemBorderStroke = pillPaginationItemBorderStroke
                ) { page ->
                    pageClicked(page)
                }
            }
        }
    }
}