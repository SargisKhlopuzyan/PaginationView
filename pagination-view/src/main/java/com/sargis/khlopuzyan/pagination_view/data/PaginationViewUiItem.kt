package com.sargis.khlopuzyan.pagination_view.data

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */
sealed interface PaginationViewUiItem

data class PaginationViewNumericUiItem(
    val page: Int,
    val uiPageIndex: Int,
    var isSelected: Boolean = false
) : PaginationViewUiItem

data class PaginationViewDotUiItem(
    val uiPageIndex: Int
) : PaginationViewUiItem

data class PaginationViewPillUiItem(
    val page: Int,
    val uiPageIndex: Int,
    var isSelected: Boolean = false
) : PaginationViewUiItem


data class PaginationState(
    val selectedPosition: Int,
    val paginationViewUiItems: List<PaginationViewUiItem>
)