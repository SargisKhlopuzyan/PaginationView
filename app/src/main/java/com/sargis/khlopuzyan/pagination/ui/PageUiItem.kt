package com.sargis.khlopuzyan.pagination.ui

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */


sealed interface PageUiItem

data class NumericPageUiItem(
    val page: Int,
    val uiPageIndex: Int,
    var isSelected: Boolean = false
) : PageUiItem

data class DotPageUiItem(
    val uiPageIndex: Int
) : PageUiItem

data class PillPageUiItem(
    val page: Int,
    val uiPageIndex: Int,
    var isSelected: Boolean = false
) : PageUiItem


data class PaginationState(
    val selectedPosition: Int,
    val pageUiItems: List<PageUiItem>
)