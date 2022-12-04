package com.sargis.khlopuzyan.pagination.ui.paginationView.numericPagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination.ui.paginationView.DotPageUiItem
import com.sargis.khlopuzyan.pagination.ui.paginationView.NumericPageUiItem
import com.sargis.khlopuzyan.pagination.ui.paginationView.PageUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun NumericPagination(
    modifier: Modifier = Modifier,
    pageUiItems: List<PageUiItem>,
    //
    numericPaginationItemContainerWidth: Dp,
    numericPaginationItemContainerHeight: Dp,
    numericPaginationItemWidth: Dp,
    numericPaginationItemHeight: Dp,
    spaceBetweenNumericPaginationItems: Dp,
    numericPaginationItemCornerRadius: Dp,
    numericPaginationItemBorderStroke: Dp,
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
                        numericPaginationItemContainerWidth = numericPaginationItemContainerWidth,
                        numericPaginationItemContainerHeight = numericPaginationItemContainerHeight,
                        numericPaginationItemWidth = numericPaginationItemWidth,
                        numericPaginationItemHeight = numericPaginationItemHeight,
                        spaceBetweenNumericPaginationItems = spaceBetweenNumericPaginationItems,
                        numericPaginationItemCornerRadius = numericPaginationItemCornerRadius,
                        numericPaginationItemBorderStroke = numericPaginationItemBorderStroke
                    ) { page ->
                        pageClicked(page)
                    }
                }
                is DotPageUiItem -> {
                    NumericPaginationDotItem(
                        numericPaginationItemContainerWidth = numericPaginationItemContainerWidth,
                        numericPaginationItemContainerHeight = numericPaginationItemContainerHeight,
                        numericPaginationItemWidth = numericPaginationItemWidth,
                        numericPaginationItemHeight = numericPaginationItemHeight,
                        spaceBetweenNumericPaginationItems = spaceBetweenNumericPaginationItems
                    )
                }
            }
        }
    }
}