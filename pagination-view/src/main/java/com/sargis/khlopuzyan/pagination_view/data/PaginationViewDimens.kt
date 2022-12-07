package com.sargis.khlopuzyan.pagination_view.data

import androidx.compose.ui.unit.Dp

/**
 * Created by Sargis Khlopuzyan on 12/6/2022.
 */
data class PaginationViewDimens(

    val paginationViewBackwardOrForwardItemDimens: PaginationViewBackwardOrForwardItemDimens,

    val paginationViewNumericItemDimens: PaginationViewItemDimens,
    val paginationViewDotItemDimens: PaginationViewItemDimens,
    val paginationViewPillItemDimens: PaginationViewItemDimens,

    val paginationViewHeight: Dp,
    val paginationViewHorizontalSpace: Dp,
    val paginationViewVerticalSpace: Dp,
    val spaceBetweenPaginationViewItems: Dp,

    val spaceBetweenBackwardOrForwardItemAndPaginationViewItem: Dp

)
