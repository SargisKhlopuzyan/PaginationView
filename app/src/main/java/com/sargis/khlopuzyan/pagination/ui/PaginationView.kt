package com.sargis.khlopuzyan.pagination.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
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
        PaginationView(paginationUiItemsChainStyle = PaginationUiItemsChainStyle.PACKED) { page ->
            Log.e("PAGINATION_VIEW", "page $page clicked")
        }
    }
}

//*****************************************************************

fun calculatePagesCount(
    containerWidth: Float,
    numberPaginationItemContainerWidth: Float,
    backwardOrForwardItemContainerWidth: Float,
    spaceBetweenNumberPaginationPageItems: Float,
    spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem: Float
): Int {
    val paginationItemsSpace =
        containerWidth - 2 * (backwardOrForwardItemContainerWidth + spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem)
    val maxCount =
        (paginationItemsSpace + spaceBetweenNumberPaginationPageItems) / (numberPaginationItemContainerWidth + spaceBetweenNumberPaginationPageItems)

    return maxCount.toInt()
}


fun initPaginationUiItems(
    itemsSize: Int,
    maxPagesCount: Int,
    selectedPageIndex: Int
): List<PageUiItem> {

    //TODO
//    if (maxPagesCount < 7) {
//        return mutableListOf()
//    }

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

private fun roundUpDivision(num: Int, divisor: Int): Int {
    return (num + divisor - 1) / divisor
}

//********************************************************************


@Composable
fun PaginationView(
    itemsSize: Int = 10, //dataList: List<Int>,
    selectedPageIndex: Int = 15, //currentPage: Int,
    alwaysShowNumber: Boolean = false,
    paginationUiItemsChainStyle: PaginationUiItemsChainStyle,
    onPageClicked: (pageNumber: Int) -> Unit
) {

    var maxPagesCount by remember {
        mutableStateOf(-1)
    }

    val paginationUiItems: List<PageUiItem> = initPaginationUiItems(
        itemsSize = itemsSize,
        maxPagesCount = maxPagesCount,
        selectedPageIndex = selectedPageIndex
    )

    var paginationState by remember {
        mutableStateOf(
            PaginationState(
                selectedPosition = selectedPageIndex,
                pageUiItems = paginationUiItems
            )
        )
    }

    Log.e("LOG_TAG", "NumericPaginationWithBackwardAndForward -> maxPagesCount : $maxPagesCount")

    val spaceBetweenNumberPaginationPageItems =
        dimensionResource(id = R.dimen.space_between_number_pagination_page_items)
    val spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem =
        dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_number_pagination_page_item)


    val numberPaginationItemWidth = dimensionResource(id = R.dimen.number_pagination_item_width)
    val numberPaginationItemHeight = dimensionResource(id = R.dimen.number_pagination_item_height)

    val numberPaginationItemContainerHeight =
        dimensionResource(id = R.dimen.number_pagination_item_container_height)
    val numberPaginationItemContainerWidth =
        dimensionResource(id = R.dimen.number_pagination_item_container_width)


    val backwardOrForwardItemWidth = dimensionResource(id = R.dimen.backward_or_forward_item_width)
    val backwardOrForwardItemHeight =
        dimensionResource(id = R.dimen.backward_or_forward_item_height)

    val backwardOrForwardItemContainerWidth =
        dimensionResource(id = R.dimen.backward_or_forward_item_container_width)
    val backwardOrForwardItemContainerHeight =
        dimensionResource(id = R.dimen.backward_or_forward_item_container_height)


    val paginationHorizontalSpace = dimensionResource(id = R.dimen.pagination_horizontal_space)
    val paginationVerticalSpace = dimensionResource(id = R.dimen.pagination_vertical_space)


    val spaceBetweenNumberPaginationPageItemsInPx =
        with(LocalDensity.current) { dimensionResource(id = R.dimen.space_between_number_pagination_page_items).toPx() }

    val spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItemInPx =
        with(LocalDensity.current) {
            dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_number_pagination_page_item).toPx()
        }

    val numberPaginationItemContainerWidthInPx =
        with(LocalDensity.current) { dimensionResource(id = R.dimen.number_pagination_item_container_width).toPx() }
//    val numberPaginationItemContainerHeightInPx =
//        with(LocalDensity.current) { dimensionResource(id = R.dimen.number_pagination_item_container_height).toPx() }


    val backwardOrForwardItemContainerWidthInPx =
        with(LocalDensity.current) { dimensionResource(id = R.dimen.backward_or_forward_item_container_height).toPx() }
//    val backwardOrForwardItemContainerHeightInPx =
//        with(LocalDensity.current) { dimensionResource(id = R.dimen.backward_or_forward_item_container_width).toPx() }


//    val numberPaginationItemWidthInPx =
//        with(LocalDensity.current) { dimensionResource(id = R.dimen.number_pagination_item_width).toPx() }
//    val numberPaginationItemHeightInPx =
//        with(LocalDensity.current) { dimensionResource(id = R.dimen.number_pagination_item_height).toPx() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = paginationHorizontalSpace, vertical = paginationVerticalSpace)
            .onGloballyPositioned { coordinates ->
                // This will be the size of the Column.
                coordinates.size
                // The position of the Column relative to the application window.
                coordinates.positionInWindow()
                // The position of the Column relative to the Compose root.
                coordinates.positionInRoot()
                // These will be the alignment lines provided to the layout (empty here for Column).
                coordinates.providedAlignmentLines
                // This will be a LayoutCoordinates instance corresponding to the parent of Column.
                coordinates.parentLayoutCoordinates

                if (maxPagesCount < 0) {

                    val containerWidth = coordinates.size.width.dp.value

                    val count = calculatePagesCount(
                        containerWidth = containerWidth,
                        numberPaginationItemContainerWidth = numberPaginationItemContainerWidthInPx,
                        backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidthInPx,
                        spaceBetweenNumberPaginationPageItems = spaceBetweenNumberPaginationPageItemsInPx,
                        spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem = spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItemInPx
                    )

                    Log.e("PAGINATION_VIEW", "Max Pages Count : $count")
                    Log.e("PAGINATION_VIEW", "coordinates.size : ${coordinates.size}")

                    maxPagesCount = count

                    paginationState = PaginationState(
                        selectedPosition = selectedPageIndex,
                        pageUiItems = initPaginationUiItems(
                            itemsSize = itemsSize,
                            maxPagesCount = maxPagesCount,
                            selectedPageIndex = selectedPageIndex
                        )
                    )
                }
            }
    ) {

        if (paginationState.pageUiItems.isNotEmpty()) {

            NumericPaginationWithBackwardAndForward(
                paginationState = paginationState,

                itemsSize = itemsSize,
                paginationUiItemsChainStyle = paginationUiItemsChainStyle,

                numberPaginationItemContainerWidth = numberPaginationItemContainerWidth,
                numberPaginationItemContainerHeight = numberPaginationItemContainerHeight,
                numberPaginationItemWidth = numberPaginationItemWidth,
                numberPaginationItemHeight = numberPaginationItemHeight,
                spaceBetweenNumberPaginationPageItems = spaceBetweenNumberPaginationPageItems,

                backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidth,
                backwardOrForwardItemContainerHeight = backwardOrForwardItemContainerHeight,
                backwardOrForwardItemWidth = backwardOrForwardItemWidth,
                backwardOrForwardItemHeight = backwardOrForwardItemHeight,
                spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem = spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem,

                onPageClicked = { page ->
                    paginationState = PaginationState(
                        selectedPosition = page,
                        pageUiItems = initPaginationUiItems(
                            itemsSize = itemsSize,
                            maxPagesCount = maxPagesCount,
                            selectedPageIndex = page
                        )
                    )
                    onPageClicked(page)
                }
            )
        }

    }

}

@Composable
fun NumericPaginationWithBackwardAndForward(

    paginationState: PaginationState,

    itemsSize: Int,
    paginationUiItemsChainStyle: PaginationUiItemsChainStyle,

    numberPaginationItemContainerWidth: Dp,
    numberPaginationItemContainerHeight: Dp,
    numberPaginationItemWidth: Dp,
    numberPaginationItemHeight: Dp,
    spaceBetweenNumberPaginationPageItems: Dp,

    backwardOrForwardItemContainerWidth: Dp,
    backwardOrForwardItemContainerHeight: Dp,
    backwardOrForwardItemWidth: Dp,
    backwardOrForwardItemHeight: Dp,
    spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem: Dp,

    onPageClicked: (pageNumber: Int) -> Unit,
) {
    val horizontalAlignment =
        if (paginationUiItemsChainStyle == PaginationUiItemsChainStyle.PACKED) {
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
                spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem = spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem
                //
            ) { page ->
                onPageClicked(page)
            }

            NumericPagination(

                modifier = Modifier.fillMaxHeight()
                    .run {
                        if (paginationUiItemsChainStyle == PaginationUiItemsChainStyle.PACKED) {
                            wrapContentWidth()
                        } else {
                            width(0.dp)
                            weight(1f)
                        }
                    },
                pageUiItems = paginationState.pageUiItems,
                //
                numberPaginationItemContainerWidth = numberPaginationItemContainerWidth,
                numberPaginationItemContainerHeight = numberPaginationItemContainerHeight,
                numberPaginationItemWidth = numberPaginationItemWidth,
                numberPaginationItemHeight = numberPaginationItemHeight,
                spaceBetweenNumberPaginationPageItems = spaceBetweenNumberPaginationPageItems
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
                spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem = spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem
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
    numberPaginationItemContainerWidth: Dp,
    numberPaginationItemContainerHeight: Dp,
    numberPaginationItemWidth: Dp,
    numberPaginationItemHeight: Dp,
    spaceBetweenNumberPaginationPageItems: Dp,
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
                    NumericPaginationNumberPageItemCompose(
                        numericPageItem = pageUiItem,
                        numericPageUiItemsSize = pageUiItems.size,
                        //
                        numberPaginationItemContainerWidth = numberPaginationItemContainerWidth,
                        numberPaginationItemContainerHeight = numberPaginationItemContainerHeight,
                        numberPaginationItemWidth = numberPaginationItemWidth,
                        numberPaginationItemHeight = numberPaginationItemHeight,
                        spaceBetweenNumberPaginationPageItems = spaceBetweenNumberPaginationPageItems
                    ) { page ->
                        pageClicked(page)
                    }
                }
                is DotPageUiItem -> {
                    NumericPaginationDotPageItemCompose(
                        numberPaginationItemContainerWidth = numberPaginationItemContainerWidth,
                        numberPaginationItemContainerHeight = numberPaginationItemContainerHeight,
                        numberPaginationItemWidth = numberPaginationItemWidth,
                        numberPaginationItemHeight = numberPaginationItemHeight,
                        spaceBetweenNumberPaginationPageItems = spaceBetweenNumberPaginationPageItems
                    )
                }
            }
        }
    }
}

@Composable
fun NumericPaginationNumberPageItemCompose(
    modifier: Modifier = Modifier,
    numericPageItem: NumericPageUiItem,
    numericPageUiItemsSize: Int,
    //
    numberPaginationItemContainerWidth: Dp,
    numberPaginationItemContainerHeight: Dp,
    numberPaginationItemWidth: Dp,
    numberPaginationItemHeight: Dp,
    spaceBetweenNumberPaginationPageItems: Dp,
    //
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        if (numericPageUiItemsSize > 1 && numericPageItem.uiPageIndex != 1) {
            Spacer(modifier = Modifier.width(spaceBetweenNumberPaginationPageItems / 2))
        }

        Box(
            modifier = modifier
                .height(numberPaginationItemContainerHeight)
                .width(numberPaginationItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(numberPaginationItemHeight)
                    .width(numberPaginationItemWidth)
                    .border(
                        border = BorderStroke(
                            width = dimensionResource(id = R.dimen.number_pagination_item_border_stroke),
                            color = if (numericPageItem.isSelected) {
                                Color.Black
                            } else {
                                Color.Transparent
                            }
                        ),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.number_pagination_item_rounded_corner))
                    )
                    .clip(
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.number_pagination_item_rounded_corner))
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
            Spacer(modifier = Modifier.width(spaceBetweenNumberPaginationPageItems / 2))
        }

    }

}

@Composable
fun NumericPaginationDotPageItemCompose(
    modifier: Modifier = Modifier,
    //
    numberPaginationItemContainerWidth: Dp,
    numberPaginationItemContainerHeight: Dp,
    numberPaginationItemWidth: Dp,
    numberPaginationItemHeight: Dp,
    spaceBetweenNumberPaginationPageItems: Dp,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        Spacer(modifier = Modifier.width(spaceBetweenNumberPaginationPageItems / 2))

        Box(
            modifier = modifier
                .height(numberPaginationItemContainerHeight)
                .width(numberPaginationItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(numberPaginationItemHeight)
                    .width(numberPaginationItemWidth)
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

        Spacer(modifier = Modifier.width(spaceBetweenNumberPaginationPageItems / 2))
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
    spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem: Dp,

    backwardOrForwardItemClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {

        if (!isBackwardIcon) {
            Spacer(
                modifier = Modifier.width(
                    spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem
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
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.number_pagination_item_rounded_corner))
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
                        .clickable {
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
                    spaceBetweenBackwardOrForwardItemAndNumberPaginationPageItem
                )
            )
        }

    }

}