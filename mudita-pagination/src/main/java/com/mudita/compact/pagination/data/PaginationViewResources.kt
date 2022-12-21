package com.mudita.compact.pagination.data

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

/**
 * Created by Sargis Khlopuzyan on 12/6/2022.
 */
data class PaginationViewResources(
    val backwardOrForwardItemResources: BackwardOrForwardItemResources,
    val numericItemResources: ItemResources,
    val dotItemResources: ItemResources,
    val pillItemResources: ItemResources,
    val paginationViewHeight: Dp,
    val paginationViewHorizontalSpace: Dp,
    val paginationViewVerticalSpace: Dp,
    val spaceBetweenPaginationViewItems: Dp,
    val spaceBetweenBackwardOrForwardItemAndPaginationViewItem: Dp,
    val paginationViewNumericItemTextStyle: TextStyle
)