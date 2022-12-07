package com.sargis.khlopuzyan.pagination_view.data

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */
sealed class PaginationViewUiItem {
    abstract val paginationViewUiItemIndex: Int
}

data class PaginationViewNumericUiItem(
    val page: Int,
    override val paginationViewUiItemIndex: Int
) : PaginationViewUiItem()

data class PaginationViewDotUiItem(
    override val paginationViewUiItemIndex: Int
) : PaginationViewUiItem()

data class PaginationViewPillUiItem(
    val page: Int,
    override val paginationViewUiItemIndex: Int
) : PaginationViewUiItem()