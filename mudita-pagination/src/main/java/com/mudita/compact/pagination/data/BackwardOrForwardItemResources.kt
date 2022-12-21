package com.mudita.compact.pagination.data

import androidx.compose.ui.unit.Dp
import com.mudita.compact.pagination.R

/**
 * Created by Sargis Khlopuzyan on 12/6/2022.
 */
data class BackwardOrForwardItemResources(
    val itemContainerWidth: Dp,
    val itemContainerHeight: Dp,
    val itemWidth: Dp,
    val itemHeight: Dp,
    val itemCornerRadius: Dp,
    val backwardActiveIconResId: Int = R.drawable.ic_arrow_left_active,
    val backwardInactiveIconResId: Int = R.drawable.ic_arrow_left_inactive,
    val forwardActiveIconResId: Int = R.drawable.ic_arrow_right_active,
    val forwardInactiveIconResId: Int = R.drawable.ic_arrow_right_inactive
)