package com.sargis.khlopuzyan.pagination_view.data

/**
 * Created by Sargis Khlopuzyan on 12/7/2022.
 */
data class PaginationViewInfo(
    val pagesSize: Int,
    val selectedPage: Int,
    val alwaysShowNumber: Boolean = false,
    val hideViewPagerInOnePageMode: Boolean = true,
    val animateOnPressEvent: Boolean,
    val paginationViewStyle: PaginationViewStyle
)