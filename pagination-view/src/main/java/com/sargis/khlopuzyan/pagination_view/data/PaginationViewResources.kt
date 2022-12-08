package com.sargis.khlopuzyan.pagination_view.data

import androidx.compose.ui.unit.Dp

/**
 * Created by Sargis Khlopuzyan on 12/6/2022.
 */
data class PaginationViewResources(

    val paginationViewBackwardOrForwardItemResources: PaginationViewBackwardOrForwardItemResources,

    val paginationViewNumericItemResources: PaginationViewItemResources,
    val paginationViewDotItemResources: PaginationViewItemResources,
    val paginationViewPillItemResources: PaginationViewItemResources,

    val paginationViewHeight: Dp,
    val paginationViewHorizontalSpace: Dp,
    val paginationViewVerticalSpace: Dp,
    val spaceBetweenPaginationViewItems: Dp,

    val spaceBetweenBackwardOrForwardItemAndPaginationViewItem: Dp

)
