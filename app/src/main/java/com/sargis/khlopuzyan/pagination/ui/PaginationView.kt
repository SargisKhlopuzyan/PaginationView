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
    paginationItemContainerWidth: Float,
    backwardOrForwardItemContainerWidth: Float,
    spaceBetweenPaginationPageItems: Float,
    spaceBetweenBackwardOrForwardItemAndPaginationPageItem: Float
): Int {
    val paginationItemsSpace =
        containerWidth - 2 * (backwardOrForwardItemContainerWidth + spaceBetweenBackwardOrForwardItemAndPaginationPageItem)
    val maxCount =
        (paginationItemsSpace + spaceBetweenPaginationPageItems) / (paginationItemContainerWidth + spaceBetweenPaginationPageItems)

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
    itemsSize: Int = 30, //dataList: List<Int>,
    selectedPageIndex: Int = 15, //currentPage: Int,
    alwaysShowNumber: Boolean = false,
    paginationUiItemsChainStyle: PaginationUiItemsChainStyle,
    onPageClicked: (pageNumber: Int) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {

    }

    NumericPaginationWithBackwardAndForward(
        itemsSize,
        selectedPageIndex,
        alwaysShowNumber,
        paginationUiItemsChainStyle,
        onPageClicked
    )
}

@Composable
fun NumericPaginationWithBackwardAndForward(
    itemsSize: Int,
    selectedPageIndex: Int,
    alwaysShowNumber: Boolean,
    paginationUiItemsChainStyle: PaginationUiItemsChainStyle,
    onPageClicked: (pageNumber: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    var maxPagesCount by remember {
        mutableStateOf(-1)
    }

    Log.e("LOG_TAG", "NumericPaginationWithBackwardAndForward -> maxPagesCount : $maxPagesCount")

    val spaceBetweenPaginationPageItems = dimensionResource(id = R.dimen.space_between_pagination_page_items)
    val spaceBetweenBackwardOrForwardItemAndPaginationPageItem = dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_pagination_page_item)


    val paginationItemWidth = dimensionResource(id = R.dimen.pagination_item_width)
    val paginationItemHeight = dimensionResource(id = R.dimen.pagination_item_height)

    val paginationItemContainerHeight = dimensionResource(id = R.dimen.pagination_item_container_height)
    val paginationItemContainerWidth = dimensionResource(id = R.dimen.pagination_item_container_width)


    val backwardOrForwardItemWidth = dimensionResource(id = R.dimen.backward_or_forward_item_width)
    val backwardOrForwardItemHeight = dimensionResource(id = R.dimen.backward_or_forward_item_height)

    val backwardOrForwardItemContainerWidth = dimensionResource(id = R.dimen.backward_or_forward_item_container_width)
    val backwardOrForwardItemContainerHeight = dimensionResource(id = R.dimen.backward_or_forward_item_container_height)


    val spaceBetweenPaginationPageItemsInPx =
        with(LocalDensity.current) { dimensionResource(id = R.dimen.space_between_pagination_page_items).toPx() }

    val spaceBetweenBackwardOrForwardItemAndPaginationPageItemInPx = with(LocalDensity.current) {
        dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_pagination_page_item).toPx()
    }

    val paginationItemContainerWidthInPx =
        with(LocalDensity.current) { dimensionResource(id = R.dimen.pagination_item_container_width).toPx() }
//    val paginationItemContainerHeightInPx =
//        with(LocalDensity.current) { dimensionResource(id = R.dimen.pagination_item_container_height).toPx() }


    val backwardOrForwardItemContainerWidthInPx =
        with(LocalDensity.current) { dimensionResource(id = R.dimen.backward_or_forward_item_container_height).toPx() }
//    val backwardOrForwardItemContainerHeightInPx =
//        with(LocalDensity.current) { dimensionResource(id = R.dimen.backward_or_forward_item_container_width).toPx() }


//    val paginationItemWidthInPx =
//        with(LocalDensity.current) { dimensionResource(id = R.dimen.pagination_item_width).toPx() }
//    val paginationItemHeightInPx =
//        with(LocalDensity.current) { dimensionResource(id = R.dimen.pagination_item_height).toPx() }


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

    Row(
        modifier = modifier
            .fillMaxSize()
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
                        paginationItemContainerWidth = paginationItemContainerWidthInPx,
                        backwardOrForwardItemContainerWidth = backwardOrForwardItemContainerWidthInPx,
                        spaceBetweenPaginationPageItems = spaceBetweenPaginationPageItemsInPx,
                        spaceBetweenBackwardOrForwardItemAndPaginationPageItem = spaceBetweenBackwardOrForwardItemAndPaginationPageItemInPx
                    )

                    Log.e("PAGINATION_VIEW", "Max Pages Count : $count")

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
            },
    ) {

        if (paginationState.pageUiItems.isNotEmpty()) {

            PaginationBackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                spaceBetweenBackwardOrForwardItemAndPaginationPageItem = spaceBetweenBackwardOrForwardItemAndPaginationPageItem,
                itemsSize = itemsSize,
                isBackwardIcon = true,
                paginationItemContainerHeight = backwardOrForwardItemContainerHeight,
                paginationItemContainerWidth = backwardOrForwardItemContainerWidth,
                paginationItemHeight = backwardOrForwardItemHeight,
                paginationItemWidth = backwardOrForwardItemWidth
            ) { page ->
                onPageClicked(page)
                paginationState = PaginationState(
                    selectedPosition = page,
                    pageUiItems = initPaginationUiItems(
                        itemsSize = itemsSize,
                        maxPagesCount = maxPagesCount,
                        selectedPageIndex = page
                    )
                )
            }

            NumericPagination(
                pageUiItems = paginationState.pageUiItems,
                spaceBetweenPaginationPageItems = spaceBetweenPaginationPageItems,
                paginationItemContainerHeight = paginationItemContainerHeight,
                paginationItemContainerWidth = paginationItemContainerWidth,
                paginationItemHeight = paginationItemHeight,
                paginationItemWidth = paginationItemWidth
            ) { page ->
                onPageClicked(page)
                paginationState = PaginationState(
                    selectedPosition = page,
                    pageUiItems = initPaginationUiItems(
                        itemsSize = itemsSize,
                        maxPagesCount = maxPagesCount,
                        selectedPageIndex = page
                    )
                )
            }

            PaginationBackwardOrForwardItemCompose(
                selectedPosition = paginationState.selectedPosition,
                spaceBetweenBackwardOrForwardItemAndPaginationPageItem = spaceBetweenBackwardOrForwardItemAndPaginationPageItem,
                itemsSize = itemsSize,
                isBackwardIcon = false,
                paginationItemContainerHeight = paginationItemContainerHeight,
                paginationItemContainerWidth = paginationItemContainerWidth,
                paginationItemHeight = paginationItemHeight,
                paginationItemWidth = paginationItemWidth
            ) { page ->
                onPageClicked(page)
                paginationState = PaginationState(
                    selectedPosition = page,
                    pageUiItems = initPaginationUiItems(
                        itemsSize = itemsSize,
                        maxPagesCount = maxPagesCount,
                        selectedPageIndex = page
                    )
                )
            }
        }
    }
}

@Composable
fun NumericPagination(
    pageUiItems: List<PageUiItem>,
    spaceBetweenPaginationPageItems: Dp,
    paginationItemContainerHeight: Dp,
    paginationItemContainerWidth: Dp,
    paginationItemHeight: Dp,
    paginationItemWidth: Dp,
    pageClicked: (page: Int) -> Unit
) {

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(40.dp)
    ) {


        val count = pageUiItems.size
        (0 until count).forEach {
            when (val pageUiItem: PageUiItem = pageUiItems[it]) {
                is NumericPageUiItem -> {
                    PageNumericItemCompose(
                        numericPageItem = pageUiItem,
                        spaceBetweenPaginationPageItems = spaceBetweenPaginationPageItems,
                        numericPageUiItemsSize = pageUiItems.size,
                        paginationItemContainerHeight = paginationItemContainerHeight,
                        paginationItemContainerWidth = paginationItemContainerWidth,
                        paginationItemHeight = paginationItemHeight,
                        paginationItemWidth = paginationItemWidth
                    ) { page ->
                        pageClicked(page)
                    }
                }
                is DotPageUiItem -> {
                    PageDotItemCompose(
                        spaceBetweenPaginationPageItems = spaceBetweenPaginationPageItems,
                        paginationItemContainerHeight = paginationItemContainerHeight,
                        paginationItemContainerWidth = paginationItemContainerWidth,
                        paginationItemHeight = paginationItemHeight,
                        paginationItemWidth = paginationItemWidth
                    )
                }
            }
        }
    }
}

@Composable
fun PageNumericItemCompose(
    modifier: Modifier = Modifier,
    spaceBetweenPaginationPageItems: Dp,
    numericPageItem: NumericPageUiItem,
    numericPageUiItemsSize: Int,
    paginationItemContainerHeight: Dp,
    paginationItemContainerWidth: Dp,
    paginationItemHeight: Dp,
    paginationItemWidth: Dp,
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        if (numericPageUiItemsSize > 1 && numericPageItem.uiPageIndex != 1) {
            Spacer(modifier = Modifier.width(spaceBetweenPaginationPageItems / 2))
        }

        Box(
            modifier = modifier
                .height(paginationItemContainerHeight)
                .width(paginationItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(paginationItemHeight)
                    .width(paginationItemWidth)
                    .border(
                        border = BorderStroke(
                            width = dimensionResource(id = R.dimen.number_pagination_item_border_stroke),
                            color = if (numericPageItem.isSelected) {
                                Color.Black
                            } else {
                                Color.White
                            }
                        ),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.number_pagination_item_rounded_corner))
                    )
                    .clip(
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.number_pagination_item_rounded_corner))
                    )
                    .clickable {
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
            Spacer(modifier = Modifier.width(spaceBetweenPaginationPageItems / 2))
        }

    }

}

@Composable
fun PageDotItemCompose(
    modifier: Modifier = Modifier,
    spaceBetweenPaginationPageItems: Dp,
    //
    paginationItemContainerHeight: Dp,
    paginationItemContainerWidth: Dp,
    paginationItemHeight: Dp,
    paginationItemWidth: Dp,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        Spacer(modifier = Modifier.width(spaceBetweenPaginationPageItems / 2))

        Box(
            modifier = modifier
                .height(paginationItemContainerHeight)
                .width(paginationItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(paginationItemHeight)
                    .width(paginationItemWidth)
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

        Spacer(modifier = Modifier.width(spaceBetweenPaginationPageItems / 2))
    }

}

@Composable
fun PaginationBackwardOrForwardItemCompose(
    modifier: Modifier = Modifier,
    selectedPosition: Int,
    spaceBetweenBackwardOrForwardItemAndPaginationPageItem: Dp,
    itemsSize: Int,
    isBackwardIcon: Boolean,
    paginationItemContainerHeight: Dp,
    paginationItemContainerWidth: Dp,
    paginationItemHeight: Dp,
    paginationItemWidth: Dp,
    backwardOrForwardItemClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        if (!isBackwardIcon) {
            Spacer(modifier = Modifier.width(spaceBetweenBackwardOrForwardItemAndPaginationPageItem))
        }

        Box(
            modifier = modifier
                .height(paginationItemContainerHeight)
                .width(paginationItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(paginationItemHeight)
                    .width(paginationItemWidth)
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
            Spacer(modifier = Modifier.width(spaceBetweenBackwardOrForwardItemAndPaginationPageItem))
        }

    }

}