package com.sargis.khlopuzyan.pagination.ui.paginationView

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.sargis.khlopuzyan.pagination.R
import com.sargis.khlopuzyan.pagination.ui.paginationView.numericPagination.NumericPaginationWithBackwardAndForward
import com.sargis.khlopuzyan.pagination.ui.paginationView.pillPagination.PillPaginationWithBackwardAndForward

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */
@Composable
fun PaginationView(
    itemsSize: Int = 0,
    selectedPageIndex: Int = 0,
    alwaysShowNumber: Boolean = false,
    paginationStyle: PaginationStyle,
    onPageClicked: (pageNumber: Int) -> Unit
) {

    var maxPagesCount by remember {
        mutableStateOf(-1)
    }

    val paginationUiItems: List<PageUiItem> = if (itemsSize > 5 || alwaysShowNumber) {
        initNumericPaginationUiItems(
            itemsSize = itemsSize,
            maxPagesCount = maxPagesCount,
            selectedPageIndex = selectedPageIndex
        )
    } else {
        initPillPaginationUiItems(
            itemsSize = itemsSize,
            maxPagesCount = maxPagesCount,
            selectedPageIndex = selectedPageIndex
        )
    }

    var paginationState by remember {
        mutableStateOf(
            PaginationState(
                selectedPosition = selectedPageIndex,
                pageUiItems = paginationUiItems
            )
        )
    }

    // PAGINATION VIEW

    val paginationHorizontalSpace = dimensionResource(id = R.dimen.pagination_horizontal_space)
    val paginationVerticalSpace = dimensionResource(id = R.dimen.pagination_vertical_space)

    // BACKWARD or FORWARD ITEM

    val backwardOrForwardItemContainerWidth =
        dimensionResource(id = R.dimen.backward_or_forward_item_container_width)
    val backwardOrForwardItemContainerHeight =
        dimensionResource(id = R.dimen.backward_or_forward_item_container_height)

    val backwardOrForwardItemWidth = dimensionResource(id = R.dimen.backward_or_forward_item_width)
    val backwardOrForwardItemHeight =
        dimensionResource(id = R.dimen.backward_or_forward_item_height)

    val backwardOrForwardItemCornerRadius = dimensionResource(id = R.dimen.backward_or_forward_item_corner_radius)

//    val backwardOrForwardItemCornerRadius = dimensionResource(id = R.dimen.pill_pagination_item_border_stroke)

    // NUMERIC PAGINATION

    val numericPaginationItemWidth = dimensionResource(id = R.dimen.numeric_pagination_item_width)
    val numericPaginationItemHeight = dimensionResource(id = R.dimen.numeric_pagination_item_height)

    val numericPaginationItemContainerWidth =
        dimensionResource(id = R.dimen.numeric_pagination_item_container_width)
    val numericPaginationItemContainerHeight =
        dimensionResource(id = R.dimen.numeric_pagination_item_container_height)


    val spaceBetweenNumericPaginationItems =
        dimensionResource(id = R.dimen.space_between_numeric_pagination_items)
    val spaceBetweenBackwardOrForwardItemAndNumericPaginationItem =
        dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_numeric_pagination_item)


    val numericPaginationItemCornerRadius = dimensionResource(id = R.dimen.numeric_pagination_item_corner_radius)
    val numericPaginationItemBorderStroke = dimensionResource(id = R.dimen.numeric_pagination_item_border_stroke)

    // PILL PAGINATION

    val pillPaginationItemContainerWidth =
        dimensionResource(id = R.dimen.pill_pagination_item_container_width)
    val pillPaginationItemContainerHeight =
        dimensionResource(id = R.dimen.pill_pagination_item_container_height)

    val pillPaginationItemWidth = dimensionResource(id = R.dimen.pill_pagination_item_width)
    val pillPaginationItemHeight = dimensionResource(id = R.dimen.pill_pagination_item_height)


    val spaceBetweenPillPaginationItems =
        dimensionResource(id = R.dimen.space_between_pill_pagination_items)
    val spaceBetweenBackwardOrForwardItemAndPillPaginationItem =
        dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_pill_pagination_item)


    val pillPaginationItemCornerRadius = dimensionResource(id = R.dimen.pill_pagination_item_corner_radius)
    val pillPaginationItemBorderStroke = dimensionResource(id = R.dimen.pill_pagination_item_border_stroke)

    //
    val density = LocalDensity.current.density

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = paginationHorizontalSpace, vertical = paginationVerticalSpace)
            .onGloballyPositioned { coordinates ->
                if (maxPagesCount < 0) {

                    val containerWidthInDp = coordinates.size.width / density

                    val maxCount = if (itemsSize > 5 || alwaysShowNumber) {
                        calculatePaginationUiItemsMaxCount(
                            containerWidth = containerWidthInDp,
                            paginationItemContainerWidth = numericPaginationItemContainerWidth.value,
                            backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth.value,
                            spaceBetweenPaginationItems = spaceBetweenNumericPaginationItems.value,
                            spaceBetweenBackwardOrForwardItemAndPaginationItem = spaceBetweenBackwardOrForwardItemAndNumericPaginationItem.value
                        )
                    } else {
                        calculatePaginationUiItemsMaxCount(
                            containerWidth = containerWidthInDp,
                            paginationItemContainerWidth = pillPaginationItemContainerWidth.value,
                            backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth.value,
                            spaceBetweenPaginationItems = spaceBetweenPillPaginationItems.value,
                            spaceBetweenBackwardOrForwardItemAndPaginationItem = spaceBetweenBackwardOrForwardItemAndPillPaginationItem.value
                        )
                    }

                    Log.e("PAGINATION_VIEW", "Pagination Ui Items Max Count : $maxCount")
                    Log.e("PAGINATION_VIEW", "coordinates.size : ${coordinates.size}")

                    maxPagesCount = maxCount

                    paginationState = PaginationState(
                        selectedPosition = selectedPageIndex,
                        pageUiItems = if (itemsSize > 5 || alwaysShowNumber) {
                            initNumericPaginationUiItems(
                                itemsSize = itemsSize,
                                maxPagesCount = maxPagesCount,
                                selectedPageIndex = selectedPageIndex
                            )
                        } else {
                            initPillPaginationUiItems(
                                itemsSize = itemsSize,
                                maxPagesCount = maxPagesCount,
                                selectedPageIndex = selectedPageIndex
                            )
                        }
                    )
                }
            }
    ) {
        if (paginationState.pageUiItems.isNotEmpty()) {

            if (paginationState.pageUiItems.size > 5 || alwaysShowNumber) {

                NumericPaginationWithBackwardAndForward(
                    paginationState = paginationState,

                    itemsSize = itemsSize,
                    paginationStyle = paginationStyle,

                    numericPaginationItemContainerWidth = numericPaginationItemContainerWidth,
                    numericPaginationItemContainerHeight = numericPaginationItemContainerHeight,
                    numericPaginationItemWidth = numericPaginationItemWidth,
                    numericPaginationItemHeight = numericPaginationItemHeight,
                    spaceBetweenNumericPaginationItems = spaceBetweenNumericPaginationItems,

                    backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                    backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                    backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                    backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                    spaceBetweenBackwardOrForwardItemAndNumericPaginationItem = spaceBetweenBackwardOrForwardItemAndNumericPaginationItem,
                    numericPaginationItemCornerRadius = numericPaginationItemCornerRadius,
                    numericPaginationItemBorderStroke = numericPaginationItemBorderStroke,
                    backwardOrForwardItemCornerRadius = backwardOrForwardItemCornerRadius,

                    onPageClicked = { page ->
                        paginationState = PaginationState(
                            selectedPosition = page,
                            pageUiItems = if (itemsSize > 5 || alwaysShowNumber) {
                                initNumericPaginationUiItems(
                                    itemsSize = itemsSize,
                                    maxPagesCount = maxPagesCount,
                                    selectedPageIndex = page
                                )
                            } else {
                                initPillPaginationUiItems(
                                    itemsSize = itemsSize,
                                    maxPagesCount = maxPagesCount,
                                    selectedPageIndex = page
                                )
                            }
                        )
                        onPageClicked(page)
                    }
                )

            } else {

                PillPaginationWithBackwardAndForward(
                    paginationState = paginationState,

                    itemsSize = itemsSize,
                    paginationStyle = paginationStyle,

                    pillPaginationItemContainerWidth = pillPaginationItemContainerWidth,
                    pillPaginationItemContainerHeight = pillPaginationItemContainerHeight,
                    pillPaginationItemWidth = pillPaginationItemWidth,
                    pillPaginationItemHeight = pillPaginationItemHeight,
                    spaceBetweenPillPaginationItems = spaceBetweenPillPaginationItems,

                    backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                    backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                    backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                    backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                    spaceBetweenBackwardOrForwardItemAndPillPaginationItem = spaceBetweenBackwardOrForwardItemAndNumericPaginationItem,
                    pillPaginationItemCornerRadius = pillPaginationItemCornerRadius,
                    pillPaginationItemBorderStroke = pillPaginationItemBorderStroke,
                    backwardOrForwardItemCornerRadius = backwardOrForwardItemCornerRadius,

                    onPageClicked = { page ->
                        paginationState = PaginationState(
                            selectedPosition = page,
                            pageUiItems = if (itemsSize > 5 || alwaysShowNumber) {
                                initNumericPaginationUiItems(
                                    itemsSize = itemsSize,
                                    maxPagesCount = maxPagesCount,
                                    selectedPageIndex = page
                                )
                            } else {
                                initPillPaginationUiItems(
                                    itemsSize = itemsSize,
                                    maxPagesCount = maxPagesCount,
                                    selectedPageIndex = page
                                )
                            }
                        )
                        onPageClicked(page)
                    }

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumericPaginationPreview() {
    MaterialTheme {
        PaginationView(paginationStyle = PaginationStyle.PACKED) { _ ->

        }
    }
}