package com.mudita.compact.pagination.data

import com.mudita.compact.pagination.util.PAGINATION_VIEW_ITEMS_MAX_COUNT
import com.mudita.compact.pagination.util.PAGINATION_VIEW_PILL_ITEMS_MAX_COUNT

/**
 * Created by Sargis Khlopuzyan on 12/7/2022.
 */
data class PaginationViewInfo(
    val pagesSize: Int = 0,
    val selectedPage: Int = 1,
    // paginationViewItemsMaxCount must be null in case we need a pagination view with auto calculated items size
    val paginationViewItemsMaxCount: Int? = PAGINATION_VIEW_ITEMS_MAX_COUNT,
    val paginationViewPillItemsMaxCount: Int = PAGINATION_VIEW_PILL_ITEMS_MAX_COUNT,
    val isAlwaysNumeric: Boolean = false,
    val hideViewPagerInOnePageMode: Boolean = true,
    val animateOnPressEvent: Boolean = false,
    val paginationViewStyle: PaginationViewStyle = PaginationViewStyle.SPREAD
)