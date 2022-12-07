package com.sargis.khlopuzyan.pagination_view.data

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */
sealed class PaginationViewUiItem {
    abstract val uiPageIndex: Int
}

data class PaginationViewNumericUiItem(
    val page: Int,
    override val uiPageIndex: Int,
    var isSelected: Boolean = false
) : PaginationViewUiItem()

data class PaginationViewDotUiItem(
    override val uiPageIndex: Int
) : PaginationViewUiItem()

data class PaginationViewPillUiItem(
    val page: Int,
    override val uiPageIndex: Int,
    var isSelected: Boolean = false
) : PaginationViewUiItem()


data class PaginationState(
    val selectedPosition: Int,
    val paginationViewUiItems: List<PaginationViewUiItem>
)