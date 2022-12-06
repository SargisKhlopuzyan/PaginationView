package com.sargis.khlopuzyan.pagination_view.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewDotUiItem
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewNumericUiItem
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewPillUiItem
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewUiItem
import com.sargis.khlopuzyan.pagination_view.paginationViewUiItems.PaginationViewDotItem
import com.sargis.khlopuzyan.pagination_view.paginationViewUiItems.PaginationViewNumericItem
import com.sargis.khlopuzyan.pagination_view.paginationViewUiItems.PaginationViewPillItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun Pagination(
    modifier: Modifier = Modifier,
    paginationViewUiItems: List<PaginationViewUiItem>,
    animateOnPressEvent: Boolean,
    //
    paginationViewItemContainerWidth: Dp,
    paginationViewItemContainerHeight: Dp,
    paginationViewItemWidth: Dp,
    paginationViewItemHeight: Dp,
    spaceBetweenPaginationViewItems: Dp,
    paginationViewItemCornerRadius: Dp,
    paginationViewItemBorderStroke: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        val count = paginationViewUiItems.size

        (0 until count).forEach {
            when (val paginationViewUiItem: PaginationViewUiItem = paginationViewUiItems[it]) {
                is PaginationViewNumericUiItem -> {
                    PaginationViewNumericItem(
                        paginationViewNumericUiItem = paginationViewUiItem,
                        paginationViewNumericUiItemsSize = paginationViewUiItems.size,
                        animateOnPressEvent = animateOnPressEvent,
                        //
                        paginationViewNumericItemContainerWidth = paginationViewItemContainerWidth,
                        paginationViewNumericItemContainerHeight = paginationViewItemContainerHeight,
                        paginationViewNumericItemWidth = paginationViewItemWidth,
                        paginationViewNumericItemHeight = paginationViewItemHeight,
                        spaceBetweenPaginationViewItems = spaceBetweenPaginationViewItems,
                        paginationViewNumericItemCornerRadius = paginationViewItemCornerRadius,
                        paginationViewNumericItemBorderStroke = paginationViewItemBorderStroke
                    ) { page ->
                        pageClicked(page)
                    }
                }
                is PaginationViewDotUiItem -> {
                    PaginationViewDotItem(
                        paginationViewDotItemContainerWidth = paginationViewItemContainerWidth,
                        paginationViewDotItemContainerHeight = paginationViewItemContainerHeight,
                        paginationViewDotItemWidth = paginationViewItemWidth,
                        paginationViewDotItemHeight = paginationViewItemHeight,
                        spaceBetweenPaginationViewItems = spaceBetweenPaginationViewItems
                    )
                }
                is PaginationViewPillUiItem -> {
                    PaginationViewPillItem(
                        pillPageUiItem = paginationViewUiItem,
                        pillPageUiItemsSize = paginationViewUiItems.size,
                        animateOnPressEvent = animateOnPressEvent,
                        //
                        paginationViewPillItemContainerWidth = paginationViewItemContainerWidth,
                        paginationViewPillItemContainerHeight = paginationViewItemContainerHeight,
                        paginationViewPillItemWidth = paginationViewItemWidth,
                        paginationViewPillItemHeight = paginationViewItemHeight,
                        spaceBetweenPaginationViewItems = spaceBetweenPaginationViewItems,
                        paginationViewPillItemCornerRadius = paginationViewItemCornerRadius,
                        paginationViewPillItemBorderStroke = paginationViewItemBorderStroke
                    ) { page ->
                        pageClicked(page)
                    }
                }
            }
        }
    }
}