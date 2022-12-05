package com.sargis.khlopuzyan.pagination.ui.paginationView

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination.R
import com.sargis.khlopuzyan.pagination.ui.paginationView.numericPagination.NumericPaginationWithBackwardAndForward
import com.sargis.khlopuzyan.pagination.ui.paginationView.pillPagination.PillPaginationWithBackwardAndForward

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaginationView(
    itemsSize: Int = 0,
    selectedPageIndex: Int = 0,
    alwaysShowNumber: Boolean = false,
    hideInOnePageMode: Boolean = true,
    paginationStyle: PaginationStyle,

    // PAGINATION VIEW

    paginationHorizontalSpace: Dp = dimensionResource(id = R.dimen.pagination_horizontal_space),
    paginationVerticalSpace: Dp = dimensionResource(id = R.dimen.pagination_vertical_space),

    // BACKWARD or FORWARD ITEM

    backwardOrForwardItemContainerWidth: Dp =
        dimensionResource(id = R.dimen.backward_or_forward_item_container_width),
    backwardOrForwardItemContainerHeight: Dp =
        dimensionResource(id = R.dimen.backward_or_forward_item_container_height),

    backwardOrForwardItemWidth: Dp = dimensionResource(id = R.dimen.backward_or_forward_item_width),
    backwardOrForwardItemHeight: Dp =
        dimensionResource(id = R.dimen.backward_or_forward_item_height),

    backwardOrForwardItemCornerRadius: Dp =
        dimensionResource(id = R.dimen.backward_or_forward_item_corner_radius),

    // NUMERIC PAGINATION

    numericPaginationItemWidth: Dp = dimensionResource(id = R.dimen.numeric_pagination_item_width),
    numericPaginationItemHeight: Dp = dimensionResource(id = R.dimen.numeric_pagination_item_height),

    numericPaginationItemContainerWidth: Dp =
        dimensionResource(id = R.dimen.numeric_pagination_item_container_width),
    numericPaginationItemContainerHeight: Dp =
        dimensionResource(id = R.dimen.numeric_pagination_item_container_height),


    spaceBetweenNumericPaginationItems: Dp =
        dimensionResource(id = R.dimen.space_between_numeric_pagination_items),
    spaceBetweenBackwardOrForwardItemAndNumericPaginationItem: Dp =
        dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_numeric_pagination_item),


    numericPaginationItemCornerRadius: Dp =
        dimensionResource(id = R.dimen.numeric_pagination_item_corner_radius),
    numericPaginationItemBorderStroke: Dp =
        dimensionResource(id = R.dimen.numeric_pagination_item_border_stroke),

    // PILL PAGINATION

    pillPaginationItemContainerWidth: Dp =
        dimensionResource(id = R.dimen.pill_pagination_item_container_width),
    pillPaginationItemContainerHeight: Dp =
        dimensionResource(id = R.dimen.pill_pagination_item_container_height),

    pillPaginationItemWidth: Dp = dimensionResource(id = R.dimen.pill_pagination_item_width),
    pillPaginationItemHeight: Dp = dimensionResource(id = R.dimen.pill_pagination_item_height),


    spaceBetweenPillPaginationItems: Dp =
        dimensionResource(id = R.dimen.space_between_pill_pagination_items),
    spaceBetweenBackwardOrForwardItemAndPillPaginationItem: Dp =
        dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_pill_pagination_item),


    pillPaginationItemCornerRadius: Dp =
        dimensionResource(id = R.dimen.pill_pagination_item_corner_radius),
    pillPaginationItemBorderStroke: Dp =
        dimensionResource(id = R.dimen.pill_pagination_item_border_stroke),


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

    val density = LocalDensity.current.density

    val squareSize = 48.dp
    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .fillMaxSize()
            .swipeable(
                state = rememberSwipeableState(
                    initialValue = swipeableState.currentValue,
                    confirmStateChange = {
                        Log.e("LOG_TAG", "rememberSwipeableState => it : $it")
                        if (it == 0 && paginationState.selectedPosition != itemsSize) {
                            paginationState = PaginationState(
                                selectedPosition = paginationState.selectedPosition + 1,
                                pageUiItems = if (itemsSize > 5 || alwaysShowNumber) {
                                    initNumericPaginationUiItems(
                                        itemsSize = itemsSize,
                                        maxPagesCount = maxPagesCount,
                                        selectedPageIndex = paginationState.selectedPosition + 1
                                    )
                                } else {
                                    initPillPaginationUiItems(
                                        itemsSize = itemsSize,
                                        maxPagesCount = maxPagesCount,
                                        selectedPageIndex = paginationState.selectedPosition + 1
                                    )
                                }
                            )
                        } else if (it == 1 && paginationState.selectedPosition != 1) {
                            paginationState = PaginationState(
                                selectedPosition = paginationState.selectedPosition - 1,
                                pageUiItems = if (itemsSize > 5 || alwaysShowNumber) {
                                    initNumericPaginationUiItems(
                                        itemsSize = itemsSize,
                                        maxPagesCount = maxPagesCount,
                                        selectedPageIndex = paginationState.selectedPosition - 1
                                    )
                                } else {
                                    initPillPaginationUiItems(
                                        itemsSize = itemsSize,
                                        maxPagesCount = maxPagesCount,
                                        selectedPageIndex = paginationState.selectedPosition - 1
                                    )
                                }
                            )
                        }
                        false
                    }), //
                anchors = anchors,
                thresholds = { from, to ->
                    Log.e("LOG_TAG", "rememberSwipeableState => from : $from  ||  to : $to")
                    FractionalThreshold(0.5f)
                },
                orientation = Orientation.Horizontal
            )
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

        val isPaginationViewVisible = paginationState.pageUiItems.isNotEmpty() && (hideInOnePageMode && paginationState.pageUiItems.size != 1)

        if (isPaginationViewVisible) {

            if (paginationState.pageUiItems.size > 5 || alwaysShowNumber) {

                NumericPaginationWithBackwardAndForward(
                    paginationState = paginationState,

                    itemsSize = itemsSize,
                    hideInOnePageMode = hideInOnePageMode,
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
                    hideInOnePageMode = hideInOnePageMode,
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