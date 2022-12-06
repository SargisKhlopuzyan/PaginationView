package com.sargis.khlopuzyan.pagination_view

import androidx.compose.ui.unit.Dp

/**
 * Created by Sargis Khlopuzyan on 12/6/2022.
 */
data class PaginationViewUiDimens(
    val paginationItemContainerWidth: Dp,
    val paginationItemContainerHeight: Dp,
    val paginationItemWidth: Dp,
    val paginationItemHeight: Dp,
    val spaceBetweenPaginationItems: Dp,

    val backwardOrForwardItemContainerWidth: Dp,
    val backwardOrForwardItemContainerHeight: Dp,
    val backwardOrForwardItemWidth: Dp,
    val backwardOrForwardItemHeight: Dp,
    val spaceBetweenBackwardOrForwardItemAndPaginationItem: Dp,
    val paginationItemCornerRadius: Dp,
    val paginationItemBorderStroke: Dp,
    val backwardOrForwardItemCornerRadius: Dp,
)
