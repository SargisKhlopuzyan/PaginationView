package com.sargis.khlopuzyan.pagination_view.data

import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.R

/**
 * Created by Sargis Khlopuzyan on 12/6/2022.
 */
data class PaginationViewBackwardOrForwardItemResources(

    val backwardOrForwardItemContainerWidth: Dp,
    val backwardOrForwardItemContainerHeight: Dp,

    val backwardOrForwardItemWidth: Dp,
    val backwardOrForwardItemHeight: Dp,

    val backwardOrForwardItemCornerRadius: Dp,

    val backwardActiveIconResId: Int = R.drawable.ic_arrow_left_active,
    val backwardInactiveIconResId: Int = R.drawable.ic_arrow_left_inactive,

    val forwardActiveIconResId: Int = R.drawable.ic_arrow_right_active,
    val forwardInactiveIconResId: Int = R.drawable.ic_arrow_right_inactive

)
