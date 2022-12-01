package com.sargis.khlopuzyan.pagination.ui

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */


sealed interface GenericPageItem

data class GenericNumericPageItem(
    val page: Int,
    val pageIndex: Int,
    var isSelected: Boolean = false
) : GenericPageItem

data class GenericDotPageItem(
    val pageIndex: Int
) : GenericPageItem
