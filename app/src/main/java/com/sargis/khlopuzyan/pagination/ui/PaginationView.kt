package com.sargis.khlopuzyan.pagination.ui

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination.R
import com.sargis.khlopuzyan.pagination.ui.theme.NumericPaginationItemText

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */

@Preview(showBackground = true)
@Composable
fun NumericPaginationPreview() {
    MaterialTheme {
        PaginationView(paginationStyle = PaginationStyle.PACKED) { page ->

        }
    }
}

//*****************************************************************

fun calculatePaginationUiItemsMaxCount(
    containerWidth: Float,
    paginationItemContainerWidth: Float,
    backwardOrForwardItemContainerWidth: Float,
    spaceBetweenPaginationItems: Float,
    spaceBetweenBackwardOrForwardItemAndPaginationItem: Float
): Int {
    val paginationItemsSpace =
        containerWidth - 2 * (backwardOrForwardItemContainerWidth + spaceBetweenBackwardOrForwardItemAndPaginationItem)
    val maxCount =
        (paginationItemsSpace + spaceBetweenPaginationItems) / (paginationItemContainerWidth + spaceBetweenPaginationItems)

    return maxCount.toInt()
}

fun initNumericPaginationUiItems(
    itemsSize: Int,
    maxPagesCount: Int,
    selectedPageIndex: Int
): List<PageUiItem> {

    val paginationItems = mutableListOf<PageUiItem>()

    when {
        itemsSize <= maxPagesCount -> {

            (1..itemsSize).forEach {

                paginationItems.add(
                    NumericPageUiItem(
                        page = it,
                        uiPageIndex = it,
                        isSelected = it == selectedPageIndex
                    )
                )

            }

        }
        else -> {

            // 1 2 3 4 5 6 |7| 8 9 10 11 ... 20
            if (selectedPageIndex <= roundUpDivision(maxPagesCount, 2)) {

                (1..maxPagesCount - 2).forEach {

                    paginationItems.add(
                        NumericPageUiItem(
                            page = it,
                            uiPageIndex = it,
                            isSelected = it == selectedPageIndex
                        )
                    )

                }

                paginationItems.add(
                    DotPageUiItem(uiPageIndex = maxPagesCount - 1)
                )

                paginationItems.add(
                    NumericPageUiItem(
                        page = itemsSize,
                        uiPageIndex = maxPagesCount,
                        isSelected = false
                    )
                )

                // 1 ... 10 11 12 13 |14| 15 16 17 18 19 20
            } else if (itemsSize - selectedPageIndex < roundUpDivision(maxPagesCount, 2)) {

                paginationItems.add(
                    NumericPageUiItem(page = 1, uiPageIndex = 1, isSelected = false)
                )

                paginationItems.add(
                    DotPageUiItem(uiPageIndex = 2)
                )

                val diff = itemsSize - maxPagesCount

                (3..maxPagesCount).forEach {

                    paginationItems.add(
                        NumericPageUiItem(
                            page = it + diff,
                            uiPageIndex = it,
                            isSelected = it + diff == selectedPageIndex
                        )
                    )

                }

                // 1 ... 6 7 8 9 |10| 11 12 13 14 ... 20
            } else {

                paginationItems.add(
                    NumericPageUiItem(page = 1, uiPageIndex = 1, isSelected = false)
                )

                paginationItems.add(
                    DotPageUiItem(uiPageIndex = 2)
                )

                val diff = selectedPageIndex - maxPagesCount / 2 - 1

                (3..maxPagesCount - 2).forEach {

                    paginationItems.add(
                        NumericPageUiItem(
                            page = it + diff,
                            uiPageIndex = it,
                            isSelected = it + diff == selectedPageIndex
                        )
                    )

                }

                paginationItems.add(
                    DotPageUiItem(uiPageIndex = maxPagesCount - 1)
                )

                paginationItems.add(
                    NumericPageUiItem(
                        page = itemsSize,
                        uiPageIndex = maxPagesCount,
                        isSelected = false
                    )
                )

            }
        }
    }

    return paginationItems
}

fun initPillPaginationUiItems(
    itemsSize: Int,
    maxPagesCount: Int,
    selectedPageIndex: Int
): List<PageUiItem> {

    val paginationItems = mutableListOf<PageUiItem>()

    (1..if (maxPagesCount > itemsSize) itemsSize else maxPagesCount).forEach {

        paginationItems.add(
            PillPageUiItem(
                page = it,
                uiPageIndex = it,
                isSelected = it == selectedPageIndex
            )
        )
    }

    return paginationItems
}


private fun roundUpDivision(num: Int, divisor: Int): Int {
    return (num + divisor - 1) / divisor
}


//********************************************************************


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

@Composable
fun NumericPaginationWithBackwardAndForward(

    paginationState: PaginationState,

    itemsSize: Int,
    paginationStyle: PaginationStyle,

    numericPaginationItemContainerWidth: Dp,
    numericPaginationItemContainerHeight: Dp,
    numericPaginationItemWidth: Dp,
    numericPaginationItemHeight: Dp,
    spaceBetweenNumericPaginationItems: Dp,

    backwardOrForwardItemContainerWidth: Dp,
    backwardOrForwardItemContainerHeight: Dp,
    backwardOrForwardItemWidth: Dp,
    backwardOrForwardItemHeight: Dp,
    spaceBetweenBackwardOrForwardItemAndNumericPaginationItem: Dp,

    onPageClicked: (pageNumber: Int) -> Unit

) {
    val horizontalAlignment = if (paginationStyle == PaginationStyle.PACKED) {
        Arrangement.Center
    } else {
        Arrangement.SpaceBetween
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = horizontalAlignment
    ) {

        if (paginationState.pageUiItems.isNotEmpty()) {

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = true,
                //
                backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationItem = spaceBetweenBackwardOrForwardItemAndNumericPaginationItem
                //
            ) { page ->
                onPageClicked(page)
            }

            NumericPagination(

                modifier = Modifier.fillMaxHeight()
                    .run {
                        if (paginationStyle == PaginationStyle.PACKED) {
                            wrapContentWidth()
                        } else {
                            width(0.dp)
                            weight(1f)
                        }
                    },
                pageUiItems = paginationState.pageUiItems,
                //
                numericPaginationItemContainerWidth = numericPaginationItemContainerWidth,
                numericPaginationItemContainerHeight = numericPaginationItemContainerHeight,
                numericPaginationItemWidth = numericPaginationItemWidth,
                numericPaginationItemHeight = numericPaginationItemHeight,
                spaceBetweenNumericPaginationItems = spaceBetweenNumericPaginationItems
                //
            ) { page ->
                onPageClicked(page)
            }

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = false,
                //
                backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationItem = spaceBetweenBackwardOrForwardItemAndNumericPaginationItem
                //
            ) { page ->
                onPageClicked(page)
            }
        }
    }
}

@Composable
fun NumericPagination(
    modifier: Modifier = Modifier,
    pageUiItems: List<PageUiItem>,
    //
    numericPaginationItemContainerWidth: Dp,
    numericPaginationItemContainerHeight: Dp,
    numericPaginationItemWidth: Dp,
    numericPaginationItemHeight: Dp,
    spaceBetweenNumericPaginationItems: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        val count = pageUiItems.size

        (0 until count).forEach {
            when (val pageUiItem: PageUiItem = pageUiItems[it]) {
                is NumericPageUiItem -> {
                    NumericPaginationNumericItemCompose(
                        numericPageItem = pageUiItem,
                        numericPageUiItemsSize = pageUiItems.size,
                        //
                        numericPaginationItemContainerWidth = numericPaginationItemContainerWidth,
                        numericPaginationItemContainerHeight = numericPaginationItemContainerHeight,
                        numericPaginationItemWidth = numericPaginationItemWidth,
                        numericPaginationItemHeight = numericPaginationItemHeight,
                        spaceBetweenNumericPaginationItems = spaceBetweenNumericPaginationItems
                    ) { page ->
                        pageClicked(page)
                    }
                }
                is DotPageUiItem -> {
                    NumericPaginationDotItemCompose(
                        numericPaginationItemContainerWidth = numericPaginationItemContainerWidth,
                        numericPaginationItemContainerHeight = numericPaginationItemContainerHeight,
                        numericPaginationItemWidth = numericPaginationItemWidth,
                        numericPaginationItemHeight = numericPaginationItemHeight,
                        spaceBetweenNumericPaginationItems = spaceBetweenNumericPaginationItems
                    )
                }
            }
        }
    }
}

@Composable
fun NumericPaginationNumericItemCompose(
    modifier: Modifier = Modifier,
    numericPageItem: NumericPageUiItem,
    numericPageUiItemsSize: Int,
    //
    numericPaginationItemContainerWidth: Dp,
    numericPaginationItemContainerHeight: Dp,
    numericPaginationItemWidth: Dp,
    numericPaginationItemHeight: Dp,
    spaceBetweenNumericPaginationItems: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        if (numericPageUiItemsSize > 1 && numericPageItem.uiPageIndex != 1) {
            Spacer(modifier = Modifier.width(spaceBetweenNumericPaginationItems / 2))
        }

        Box(
            modifier = modifier
                .height(numericPaginationItemContainerHeight)
                .width(numericPaginationItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(numericPaginationItemHeight)
                    .width(numericPaginationItemWidth)
                    .border(
                        border = BorderStroke(
                            width = dimensionResource(id = R.dimen.numeric_pagination_item_border_stroke),
                            color = if (numericPageItem.isSelected) {
                                Color.Black
                            } else {
                                Color.Transparent
                            }
                        ),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.numeric_pagination_item_corner_radius))
                    )
                    .clip(
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.numeric_pagination_item_corner_radius))
                    )
                    .clickable(
                        // Uncomment to disable ripple effect when clicking
//                        indication = null, interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (!numericPageItem.isSelected) {
                            pageClicked(numericPageItem.page)
                        }
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "${numericPageItem.page}",
                    style = NumericPaginationItemText,
                    color = Color.Black
                )
            }
        }

        if (numericPageUiItemsSize > 1 && numericPageItem.uiPageIndex != numericPageUiItemsSize) {
            Spacer(modifier = Modifier.width(spaceBetweenNumericPaginationItems / 2))
        }

    }

}

@Composable
fun NumericPaginationDotItemCompose(
    modifier: Modifier = Modifier,
    //
    numericPaginationItemContainerWidth: Dp,
    numericPaginationItemContainerHeight: Dp,
    numericPaginationItemWidth: Dp,
    numericPaginationItemHeight: Dp,
    spaceBetweenNumericPaginationItems: Dp,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        Spacer(modifier = Modifier.width(spaceBetweenNumericPaginationItems / 2))

        Box(
            modifier = modifier
                .height(numericPaginationItemContainerHeight)
                .width(numericPaginationItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(numericPaginationItemHeight)
                    .width(numericPaginationItemWidth)
            ) {
                Text(
                    modifier = Modifier
                        .matchParentSize()
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter),
                    text = "...",
                    textAlign = TextAlign.Center,
                    style = NumericPaginationItemText,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.width(spaceBetweenNumericPaginationItems / 2))
    }

}

@Composable
fun BackwardOrForwardItemCompose(
    selectedPosition: Int,
    itemsSize: Int,
    isBackwardIcon: Boolean,

    backwardOrForwardItemContainerWidth: Dp,
    backwardOrForwardItemContainerHeight: Dp,
    backwardOrForwardItemWidth: Dp,
    backwardOrForwardItemHeight: Dp,
    spaceBetweenBackwardOrForwardItemAndPaginationItem: Dp,

    backwardOrForwardItemClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {

        if (!isBackwardIcon) {
            Spacer(
                modifier = Modifier.width(
                    spaceBetweenBackwardOrForwardItemAndPaginationItem
                )
            )
        }

        Box(
            modifier = Modifier
                .height(backwardOrForwardItemContainerHeight)
                .width(backwardOrForwardItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(backwardOrForwardItemHeight)
                    .width(backwardOrForwardItemWidth)
                    .clip(
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.pill_pagination_item_corner_radius))
                    )
            ) {

                val iconPainter = if (isBackwardIcon) {
                    if (selectedPosition == 1) {
                        painterResource(id = R.drawable.ic_arrow_left_inactive)
                    } else {
                        painterResource(id = R.drawable.ic_arrow_left_active)
                    }
                } else {
                    if (selectedPosition == itemsSize) {
                        painterResource(id = R.drawable.ic_arrow_right_inactive)
                    } else {
                        painterResource(id = R.drawable.ic_arrow_right_active)
                    }
                }

                Image(
                    painter = iconPainter,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .matchParentSize()
                        .clickable(
                            // Uncomment to disable ripple effect when clicking
//                        indication = null, interactionSource = remember { MutableInteractionSource() }
                        ) {
                            if (isBackwardIcon && selectedPosition != 1) {
                                backwardOrForwardItemClicked(selectedPosition - 1)
                            } else if (!isBackwardIcon && selectedPosition != itemsSize) {
                                backwardOrForwardItemClicked(selectedPosition + 1)
                            }
                        }
                )
            }
        }

        if (isBackwardIcon) {
            Spacer(
                modifier = Modifier.width(
                    spaceBetweenBackwardOrForwardItemAndPaginationItem
                )
            )
        }

    }

}


@Composable
fun PillPaginationWithBackwardAndForward(

    paginationState: PaginationState,

    itemsSize: Int,
    paginationStyle: PaginationStyle,

    pillPaginationItemContainerWidth: Dp,
    pillPaginationItemContainerHeight: Dp,
    pillPaginationItemWidth: Dp,
    pillPaginationItemHeight: Dp,
    spaceBetweenPillPaginationItems: Dp,

    backwardOrForwardItemContainerWidth: Dp,
    backwardOrForwardItemContainerHeight: Dp,
    backwardOrForwardItemWidth: Dp,
    backwardOrForwardItemHeight: Dp,
    spaceBetweenBackwardOrForwardItemAndPillPaginationItem: Dp,

    onPageClicked: (pageNumber: Int) -> Unit,
) {
    val horizontalAlignment =
        if (paginationStyle == PaginationStyle.PACKED) {
            Arrangement.Center
        } else {
            Arrangement.SpaceBetween
        }
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = horizontalAlignment
    ) {

        if (paginationState.pageUiItems.isNotEmpty()) {

            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = true,
                //
                backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationItem = spaceBetweenBackwardOrForwardItemAndPillPaginationItem
                //
            ) { page ->
                onPageClicked(page)
            }

            PillPagination(

                modifier = Modifier.fillMaxHeight()
                    .run {
                        if (paginationStyle == PaginationStyle.PACKED) {
                            wrapContentWidth()
                        } else {
                            width(0.dp)
                            weight(1f)
                        }
                    },
                pageUiItems = paginationState.pageUiItems,
                //
                pillPaginationItemContainerWidth = pillPaginationItemContainerWidth,
                pillPaginationItemContainerHeight = pillPaginationItemContainerHeight,
                pillPaginationItemWidth = pillPaginationItemWidth,
                pillPaginationItemHeight = pillPaginationItemHeight,
                spaceBetweenPillPaginationItems = spaceBetweenPillPaginationItems
                //
            ) { page ->
                onPageClicked(page)
            }


            BackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                itemsSize = itemsSize,
                isBackwardIcon = false,
                //
                backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndPaginationItem = spaceBetweenBackwardOrForwardItemAndPillPaginationItem
                //
            ) { page ->
                onPageClicked(page)
            }
        }
    }
}


@Composable
fun PillPagination(
    modifier: Modifier = Modifier,
    pageUiItems: List<PageUiItem>,
    //
    pillPaginationItemContainerWidth: Dp,
    pillPaginationItemContainerHeight: Dp,
    pillPaginationItemWidth: Dp,
    pillPaginationItemHeight: Dp,
    spaceBetweenPillPaginationItems: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        val count = pageUiItems.size

        (0 until count).forEach {
            val pageUiItem: PageUiItem? = pageUiItems.getOrNull(it)
            if (pageUiItem is PillPageUiItem) {
                PillPaginationItemCompose(
                    pillPageUiItem = pageUiItem,
                    pillPageUiItemsSize = pageUiItems.size,
                    //
                    pillPaginationItemContainerWidth = pillPaginationItemContainerWidth,
                    pillPaginationItemContainerHeight = pillPaginationItemContainerHeight,
                    pillPaginationItemWidth = pillPaginationItemWidth,
                    pillPaginationItemHeight = pillPaginationItemHeight,
                    spaceBetweenPillPaginationItems = spaceBetweenPillPaginationItems
                ) { page ->
                    pageClicked(page)
                }
            }
        }
    }
}


@Composable
fun PillPaginationItemCompose(
    modifier: Modifier = Modifier,
    pillPageUiItem: PillPageUiItem,
    pillPageUiItemsSize: Int,
    //
    pillPaginationItemContainerWidth: Dp,
    pillPaginationItemContainerHeight: Dp,
    pillPaginationItemWidth: Dp,
    pillPaginationItemHeight: Dp,
    spaceBetweenPillPaginationItems: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {

        if (pillPageUiItemsSize > 1 && pillPageUiItem.uiPageIndex != 1) {
            Spacer(modifier = Modifier.width(spaceBetweenPillPaginationItems / 2))
        }

        Box(
            modifier = modifier
                .width(pillPaginationItemContainerWidth)
                .height(pillPaginationItemContainerHeight),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .width(pillPaginationItemWidth)
                    .height(pillPaginationItemHeight)
                    .border(
                        border = BorderStroke(
                            width = dimensionResource(id = R.dimen.pill_pagination_item_border_stroke),
                            color = Color.Black
                        ),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.pill_pagination_item_corner_radius))
                    )
                    .clip(
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.pill_pagination_item_corner_radius))
                    )
                    .background(
                        if (pillPageUiItem.isSelected) {
                            Color.Black
                        } else {
                            Color.Transparent
                        }
                    )
                    .clickable(
                        // Uncomment to disable ripple effect when clicking
//                        indication = null, interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (!pillPageUiItem.isSelected) {
                            pageClicked(pillPageUiItem.page)
                        }
                    }
            )
        }

        if (pillPageUiItemsSize > 1 && pillPageUiItem.uiPageIndex != pillPageUiItemsSize) {
            Spacer(modifier = Modifier.width(spaceBetweenPillPaginationItems / 2))
        }

    }

}