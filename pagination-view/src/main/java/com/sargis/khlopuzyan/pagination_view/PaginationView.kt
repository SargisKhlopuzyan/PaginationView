package com.sargis.khlopuzyan.pagination_view

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
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
import com.sargis.khlopuzyan.pagination_view.data.PaginationState
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewUiDimens
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewUiItem
import com.sargis.khlopuzyan.pagination_view.pagination.PaginationWithBackwardAndForward
import com.sargis.khlopuzyan.pagination_view.util.calculatePaginationViewUiItemsMaxCount
import com.sargis.khlopuzyan.pagination_view.util.initPaginationViewUiItems

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaginationView(
    modifier: Modifier = Modifier,
    pages: List<Int> = listOf(),
    selectedPageIndex: Int = 0,
    alwaysShowNumber: Boolean = false,
    hideViewPagerInOnePageMode: Boolean = true,
    animateOnPressEvent: Boolean = false,
    paginationViewStyle: PaginationViewStyle,

    // PAGINATION VIEW

    paginationHorizontalSpace: Dp = dimensionResource(id = R.dimen.pagination_view_horizontal_space),
    paginationVerticalSpace: Dp = dimensionResource(id = R.dimen.pagination_view_vertical_space),

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

    paginationViewNumericItemWidth: Dp = dimensionResource(id = R.dimen.pagination_view_numeric_item_width),
    paginationViewNumericItemHeight: Dp = dimensionResource(id = R.dimen.pagination_view_numeric_item_height),

    paginationViewNumericItemContainerWidth: Dp =
        dimensionResource(id = R.dimen.pagination_view_numeric_item_container_width),
    paginationViewNumericItemContainerHeight: Dp =
        dimensionResource(id = R.dimen.pagination_view_numeric_item_container_height),


    spaceBetweenPaginationViewItems: Dp = dimensionResource(id = R.dimen.space_between_pagination_view_items),
    spaceBetweenBackwardOrForwardItemAndPaginationViewItem: Dp =
        dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_pagination_view_item),


    paginationViewNumericItemCornerRadius: Dp =
        dimensionResource(id = R.dimen.pagination_view_numeric_item_corner_radius),
    paginationViewNumericItemBorderStroke: Dp =
        dimensionResource(id = R.dimen.pagination_view_numeric_item_border_stroke),

    // PILL PAGINATION

    paginationViewPillItemContainerWidth: Dp =
        dimensionResource(id = R.dimen.pagination_view_pill_item_container_width),
    paginationViewPillItemContainerHeight: Dp =
        dimensionResource(id = R.dimen.pagination_view_pill_item_container_height),

    paginationViewPillItemWidth: Dp = dimensionResource(id = R.dimen.pagination_view_pill_item_width),
    paginationViewPillItemHeight: Dp = dimensionResource(id = R.dimen.pagination_view_pill_item_height),


    paginationViewPillItemCornerRadius: Dp =
        dimensionResource(id = R.dimen.pagination_view_pill_item_corner_radius),
    paginationViewPillItemBorderStroke: Dp =
        dimensionResource(id = R.dimen.pagination_view_pill_item_border_stroke),


    onPageClicked: (pageNumber: Int) -> Unit
) {

    var paginationViewUiItemsMaxCount by remember {
        mutableStateOf(-1)
    }

    val paginationViewUiItems: List<PaginationViewUiItem> = initPaginationViewUiItems(
        pagesSize = pages.size,
        alwaysShowNumber = alwaysShowNumber,
        paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount,
        selectedPageIndex = selectedPageIndex
    )

    var paginationState by remember {
        mutableStateOf(
            PaginationState(
                selectedPosition = selectedPageIndex,
                paginationViewUiItems = paginationViewUiItems
            )
        )
    }

    val density = LocalDensity.current.density

    val squareSize = 48.dp
    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = modifier
            .swipeable(
                state = rememberSwipeableState(
                    initialValue = swipeableState.currentValue,
                    confirmStateChange = {
                        if (it == 0 && paginationState.selectedPosition != pages.size) {
                            paginationState = PaginationState(
                                selectedPosition = paginationState.selectedPosition + 1,
                                paginationViewUiItems = initPaginationViewUiItems(
                                    pagesSize = pages.size,
                                    alwaysShowNumber = alwaysShowNumber,
                                    paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount,
                                    selectedPageIndex = paginationState.selectedPosition + 1
                                )
                            )
                        } else if (it == 1 && paginationState.selectedPosition != 1) {
                            paginationState = PaginationState(
                                selectedPosition = paginationState.selectedPosition - 1,
                                paginationViewUiItems = initPaginationViewUiItems(
                                    pagesSize = pages.size,
                                    alwaysShowNumber = alwaysShowNumber,
                                    paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount,
                                    selectedPageIndex = paginationState.selectedPosition - 1
                                )
                            )
                        }
                        false
                    }), //
                anchors = anchors,
                thresholds = { from, to ->
                    FractionalThreshold(0.5f)
                },
                orientation = Orientation.Horizontal
            )
            .padding(horizontal = paginationHorizontalSpace, vertical = paginationVerticalSpace)
            .onGloballyPositioned { coordinates ->
                if (paginationViewUiItemsMaxCount < 0) {

                    val containerWidthInDp = coordinates.size.width / density

                    val maxCount = if (pages.size > 5 || alwaysShowNumber) {
                        calculatePaginationViewUiItemsMaxCount(
                            containerWidth = containerWidthInDp,
                            paginationViewItemContainerWidth = paginationViewNumericItemContainerWidth.value,
                            backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth.value,
                            spaceBetweenPaginationViewItems = spaceBetweenPaginationViewItems.value,
                            spaceBetweenBackwardOrForwardItemAndPaginationViewItem = spaceBetweenBackwardOrForwardItemAndPaginationViewItem.value
                        )
                    } else {
                        calculatePaginationViewUiItemsMaxCount(
                            containerWidth = containerWidthInDp,
                            paginationViewItemContainerWidth = paginationViewPillItemContainerWidth.value,
                            backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth.value,
                            spaceBetweenPaginationViewItems = spaceBetweenPaginationViewItems.value,
                            spaceBetweenBackwardOrForwardItemAndPaginationViewItem = spaceBetweenBackwardOrForwardItemAndPaginationViewItem.value
                        )
                    }

                    paginationViewUiItemsMaxCount = maxCount

                    paginationState = PaginationState(
                        selectedPosition = selectedPageIndex,
                        paginationViewUiItems = initPaginationViewUiItems(
                            pagesSize = pages.size,
                            alwaysShowNumber = alwaysShowNumber,
                            paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount,
                            selectedPageIndex = selectedPageIndex
                        )
                    )
                }
            }
    ) {

        val paginationViewUiDimens =
            if (paginationState.paginationViewUiItems.size > 5 || alwaysShowNumber) {
                PaginationViewUiDimens(
                    paginationViewItemContainerWidth = paginationViewNumericItemContainerWidth,
                    paginationViewItemContainerHeight = paginationViewNumericItemContainerHeight,
                    paginationViewItemWidth = paginationViewNumericItemWidth,
                    paginationViewItemHeight = paginationViewNumericItemHeight,
                    spaceBetweenPaginationViewItems = spaceBetweenPaginationViewItems,

                    backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                    backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                    backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                    backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                    spaceBetweenBackwardOrForwardItemAndPaginationViewItem = spaceBetweenBackwardOrForwardItemAndPaginationViewItem,
                    paginationViewItemCornerRadius = paginationViewNumericItemCornerRadius,
                    paginationViewItemBorderStroke = paginationViewNumericItemBorderStroke,
                    backwardOrForwardItemCornerRadius = backwardOrForwardItemCornerRadius,
                )
            } else {
                PaginationViewUiDimens(
                    paginationViewItemContainerWidth = paginationViewPillItemContainerWidth,
                    paginationViewItemContainerHeight = paginationViewPillItemContainerHeight,
                    paginationViewItemWidth = paginationViewPillItemWidth,
                    paginationViewItemHeight = paginationViewPillItemHeight,
                    spaceBetweenPaginationViewItems = spaceBetweenPaginationViewItems,

                    backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                    backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                    backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                    backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                    spaceBetweenBackwardOrForwardItemAndPaginationViewItem = spaceBetweenBackwardOrForwardItemAndPaginationViewItem,
                    paginationViewItemCornerRadius = paginationViewPillItemCornerRadius,
                    paginationViewItemBorderStroke = paginationViewPillItemBorderStroke,
                    backwardOrForwardItemCornerRadius = backwardOrForwardItemCornerRadius,
                )
            }

        PaginationWithBackwardAndForward(
            paginationState = paginationState,

            itemsSize = pages.size,
            hideViewPagerInOnePageMode = hideViewPagerInOnePageMode,
            paginationViewStyle = paginationViewStyle,
            animateOnPressEvent = animateOnPressEvent,
            paginationViewUiDimens = paginationViewUiDimens,

            onPageClicked = { page ->
                paginationState = PaginationState(
                    selectedPosition = page,
                    paginationViewUiItems = initPaginationViewUiItems(
                        pagesSize = pages.size,
                        alwaysShowNumber = alwaysShowNumber,
                        paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount,
                        selectedPageIndex = page
                    )
                )
                onPageClicked(page)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaginationViewPreview() {
    MaterialTheme {
        PaginationView(paginationViewStyle = PaginationViewStyle.PACKED) { _ ->

        }
    }
}