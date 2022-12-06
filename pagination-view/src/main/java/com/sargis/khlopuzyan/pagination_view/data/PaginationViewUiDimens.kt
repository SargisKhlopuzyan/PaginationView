package com.sargis.khlopuzyan.pagination_view.data

import androidx.compose.ui.unit.Dp

/**
 * Created by Sargis Khlopuzyan on 12/6/2022.
 */
data class PaginationViewUiDimens(
    val paginationViewItemContainerWidth: Dp,
    val paginationViewItemContainerHeight: Dp,
    val paginationViewItemWidth: Dp,
    val paginationViewItemHeight: Dp,
    val spaceBetweenPaginationViewItems: Dp,

    val backwardOrForwardItemContainerWidth: Dp,
    val backwardOrForwardItemContainerHeight: Dp,
    val backwardOrForwardItemWidth: Dp,
    val backwardOrForwardItemHeight: Dp,
    val spaceBetweenBackwardOrForwardItemAndPaginationViewItem: Dp,
    val paginationViewItemCornerRadius: Dp,
    val paginationViewItemBorderStroke: Dp,
    val backwardOrForwardItemCornerRadius: Dp,
)
