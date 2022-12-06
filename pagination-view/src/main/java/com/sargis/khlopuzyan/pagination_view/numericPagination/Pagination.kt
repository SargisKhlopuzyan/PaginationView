package com.sargis.khlopuzyan.pagination_view.numericPagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.DotPageUiItem
import com.sargis.khlopuzyan.pagination_view.NumericPageUiItem
import com.sargis.khlopuzyan.pagination_view.PageUiItem
import com.sargis.khlopuzyan.pagination_view.PillPageUiItem
import com.sargis.khlopuzyan.pagination_view.pillPagination.PillPaginationItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun Pagination(
    modifier: Modifier = Modifier,
    pageUiItems: List<PageUiItem>,
    //
    paginationItemContainerWidth: Dp,
    paginationItemContainerHeight: Dp,
    paginationItemWidth: Dp,
    paginationItemHeight: Dp,
    spaceBetweenPaginationItems: Dp,
    paginationItemCornerRadius: Dp,
    paginationItemBorderStroke: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        val count = pageUiItems.size

        (0 until count).forEach {
            when (val pageUiItem: PageUiItem = pageUiItems[it]) {
                is NumericPageUiItem -> {
                    NumericPaginationNumericItem(
                        numericPageItem = pageUiItem,
                        numericPageUiItemsSize = pageUiItems.size,
                        //
                        numericPaginationItemContainerWidth = paginationItemContainerWidth,
                        numericPaginationItemContainerHeight = paginationItemContainerHeight,
                        numericPaginationItemWidth = paginationItemWidth,
                        numericPaginationItemHeight = paginationItemHeight,
                        spaceBetweenPaginationItems = spaceBetweenPaginationItems,
                        numericPaginationItemCornerRadius = paginationItemCornerRadius,
                        numericPaginationItemBorderStroke = paginationItemBorderStroke
                    ) { page ->
                        pageClicked(page)
                    }
                }
                is DotPageUiItem -> {
                    NumericPaginationDotItem(
                        numericPaginationItemContainerWidth = paginationItemContainerWidth,
                        numericPaginationItemContainerHeight = paginationItemContainerHeight,
                        numericPaginationItemWidth = paginationItemWidth,
                        numericPaginationItemHeight = paginationItemHeight,
                        spaceBetweenPaginationItems = spaceBetweenPaginationItems
                    )
                }
                is PillPageUiItem -> {
                    PillPaginationItem(
                        pillPageUiItem = pageUiItem,
                        pillPageUiItemsSize = pageUiItems.size,
                        //
                        pillPaginationItemContainerWidth = paginationItemContainerWidth,
                        pillPaginationItemContainerHeight = paginationItemContainerHeight,
                        pillPaginationItemWidth = paginationItemWidth,
                        pillPaginationItemHeight = paginationItemHeight,
                        spaceBetweenPaginationItems = spaceBetweenPaginationItems,
                        pillPaginationItemCornerRadius = paginationItemCornerRadius,
                        pillPaginationItemBorderStroke = paginationItemBorderStroke
                    ) { page ->
                        pageClicked(page)
                    }
                }
            }
        }
    }
}