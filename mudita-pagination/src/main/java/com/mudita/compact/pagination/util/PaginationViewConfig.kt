package com.mudita.compact.pagination.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.mudita.compact.pagination.R
import com.mudita.compact.pagination.data.BackwardOrForwardItemResources
import com.mudita.compact.pagination.data.ItemResources
import com.mudita.compact.pagination.data.PaginationViewResources
import com.mudita.compact.pagination.theme.PaginationViewNumericItemText

/**
 * Created by Sargis Khlopuzyan on 12/14/2022.
 */
@Composable
fun initPaginationViewDefaultResources() = PaginationViewResources(

    // PAGINATION VIEW
    paginationViewHeight = dimensionResource(id = R.dimen.pagination_View_height),
    paginationViewHorizontalSpace = dimensionResource(id = R.dimen.pagination_view_horizontal_space),
    paginationViewVerticalSpace = dimensionResource(id = R.dimen.pagination_view_vertical_space),
    spaceBetweenPaginationViewItems = dimensionResource(id = R.dimen.space_between_pagination_view_items),
    spaceBetweenBackwardOrForwardItemAndPaginationViewItem = dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_pagination_view_item),

    paginationViewNumericItemTextStyle = PaginationViewNumericItemText,

    // BACKWARD or FORWARD ITEM
    backwardOrForwardItemResources = BackwardOrForwardItemResources(
        itemContainerWidth = dimensionResource(id = R.dimen.backward_or_forward_item_container_width),
        itemContainerHeight = dimensionResource(id = R.dimen.backward_or_forward_item_container_height),

        itemWidth = dimensionResource(id = R.dimen.backward_or_forward_item_width),
        itemHeight = dimensionResource(id = R.dimen.backward_or_forward_item_height),

        itemCornerRadius = dimensionResource(id = R.dimen.backward_or_forward_item_corner_radius)
    ),

    // PAGINATION VIEW NUMERIC ITEM
    numericItemResources = ItemResources(
        itemWidth = dimensionResource(id = R.dimen.pagination_view_numeric_item_width),
        itemHeight = dimensionResource(id = R.dimen.pagination_view_numeric_item_height),

        itemContainerWidth = dimensionResource(id = R.dimen.pagination_view_numeric_item_container_width),
        itemContainerHeight = dimensionResource(id = R.dimen.pagination_view_numeric_item_container_height),

        itemCornerRadius = dimensionResource(id = R.dimen.pagination_view_numeric_item_corner_radius),
        itemBorderStroke = dimensionResource(id = R.dimen.pagination_view_numeric_item_border_stroke),

        spaceBetweenPaginationViewItems = dimensionResource(id = R.dimen.space_between_pagination_view_items)
    ),

    // PAGINATION VIEW DOT ITEM
    dotItemResources = ItemResources(
        itemWidth = dimensionResource(id = R.dimen.pagination_view_numeric_item_width),
        itemHeight = dimensionResource(id = R.dimen.pagination_view_numeric_item_height),

        itemContainerWidth = dimensionResource(id = R.dimen.pagination_view_numeric_item_container_width),
        itemContainerHeight = dimensionResource(id = R.dimen.pagination_view_numeric_item_container_height),

        itemCornerRadius = dimensionResource(id = R.dimen.pagination_view_numeric_item_corner_radius),
        itemBorderStroke = dimensionResource(id = R.dimen.pagination_view_numeric_item_border_stroke),

        spaceBetweenPaginationViewItems = dimensionResource(id = R.dimen.space_between_pagination_view_items)
    ),

    // PAGINATION VIEW PILL ITEM
    pillItemResources = ItemResources(
        itemContainerWidth = dimensionResource(id = R.dimen.pagination_view_pill_item_container_width),
        itemContainerHeight = dimensionResource(id = R.dimen.pagination_view_pill_item_container_height),

        itemWidth = dimensionResource(id = R.dimen.pagination_view_pill_item_width),
        itemHeight = dimensionResource(id = R.dimen.pagination_view_pill_item_height),

        itemCornerRadius = dimensionResource(id = R.dimen.pagination_view_pill_item_corner_radius),
        itemBorderStroke = dimensionResource(id = R.dimen.pagination_view_pill_item_border_stroke),

        spaceBetweenPaginationViewItems = dimensionResource(id = R.dimen.space_between_pagination_view_items)
    )
)