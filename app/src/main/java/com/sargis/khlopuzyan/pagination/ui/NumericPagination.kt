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
        NumericPaginationWithBackwardAndForward(PaginationUiItemsChainStyle.SPREAD)
//        NumericPagination()
    }
}

@Composable
fun NumericPaginationWithChainStyle(paginationUiItemsChainStyle: PaginationUiItemsChainStyle) {

}

@Composable
fun NumericPaginationWithBackwardAndForward(paginationUiItemsChainStyle: PaginationUiItemsChainStyle) {

//    when (paginationUiItemsChainStyle) {
//        PaginationUiItemsChainStyle.PACKED -> {
//
//            Row {
//            }
//
//        }
//        PaginationUiItemsChainStyle.SPREAD -> {
//
//        }
//        PaginationUiItemsChainStyle.SPREAD_INSIDE -> {
//
//        }
//    }

    val spaceBetweenPaginationPageItems =
        dimensionResource(id = R.dimen.space_between_pagination_page_items)

    val spaceBetweenBackwardOrForwardItemAndPaginationPageItem =
        dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_pagination_page_item)

    val paginationItemContainerHeight =
        dimensionResource(id = R.dimen.pagination_item_container_height)
    val paginationItemContainerWidth =
        dimensionResource(id = R.dimen.pagination_item_container_width)

    val paginationItemHeight = dimensionResource(id = R.dimen.pagination_item_height)
    val paginationItemWidth = dimensionResource(id = R.dimen.pagination_item_width)

    val selectedPageIndex = 1
    val maxPagesCount = 13
    val itemsSize = 30

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

    var selectedPageIndexState by remember {
        mutableStateOf(selectedPageIndex)
    }

    var paginationUiItemsState by remember {
        mutableStateOf(paginationUiItems)
    }


    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(40.dp)
    ) {

        PaginationBackwardOrForwardItemCompose(
            spaceBetweenBackwardOrForwardItemAndPaginationPageItem = spaceBetweenBackwardOrForwardItemAndPaginationPageItem,
            selectedPageIndexState = selectedPageIndexState,
            itemsSize = itemsSize,
            isBackwardIcon = true,
            paginationItemContainerHeight = paginationItemContainerHeight,
            paginationItemContainerWidth = paginationItemContainerWidth,
            paginationItemHeight = paginationItemHeight,
            paginationItemWidth = paginationItemWidth
        ) { page ->
            val newPaginationUiItems = initPaginationUiItems(
                itemsSize = itemsSize,
                maxPagesCount = maxPagesCount,
                selectedPageIndex = page
            )
            paginationUiItemsState = newPaginationUiItems
            selectedPageIndexState = page
        }

        NumericPagination(
            numericPageUiItems = paginationUiItemsState,
            spaceBetweenPaginationPageItems = spaceBetweenPaginationPageItems,
            paginationUiItemsState = paginationUiItemsState,
            paginationItemContainerHeight = paginationItemContainerHeight,
            paginationItemContainerWidth = paginationItemContainerWidth,
            paginationItemHeight = paginationItemHeight,
            paginationItemWidth = paginationItemWidth
        ) { page ->
            val newPaginationUiItems = initPaginationUiItems(
                itemsSize = itemsSize,
                maxPagesCount = maxPagesCount,
                selectedPageIndex = page
            )
            paginationUiItemsState = newPaginationUiItems
            selectedPageIndexState = page
        }

        PaginationBackwardOrForwardItemCompose(
            spaceBetweenBackwardOrForwardItemAndPaginationPageItem = spaceBetweenBackwardOrForwardItemAndPaginationPageItem,
            selectedPageIndexState = selectedPageIndexState,
            itemsSize = itemsSize,
            isBackwardIcon = false,
            paginationItemContainerHeight = paginationItemContainerHeight,
            paginationItemContainerWidth = paginationItemContainerWidth,
            paginationItemHeight = paginationItemHeight,
            paginationItemWidth = paginationItemWidth
        ) { page ->
            val newPaginationUiItems = initPaginationUiItems(
                itemsSize = itemsSize,
                maxPagesCount = maxPagesCount,
                selectedPageIndex = page
            )
            paginationUiItemsState = newPaginationUiItems
            selectedPageIndexState = page
        }
    }

}

@Composable
fun NumericPagination(
    numericPageUiItems: List<PageUiItem>,
    spaceBetweenPaginationPageItems: Dp,
    paginationUiItemsState: List<PageUiItem>,
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

        val count = paginationUiItemsState.size
        (0 until count).forEach {
            when (val pageUiItem: PageUiItem = paginationUiItemsState[it]) {
                is NumericPageUiItem -> {
                    PageNumericItemCompose(
                        numericPageItem = pageUiItem,
                        spaceBetweenPaginationPageItems = spaceBetweenPaginationPageItems,
                        numericPageUiItemsSize = numericPageUiItems.size,
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

fun initPaginationUiItems(
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

private fun roundUpDivision(num: Int, divisor: Int): Int {
    return (num + divisor - 1) / divisor
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
    spaceBetweenBackwardOrForwardItemAndPaginationPageItem: Dp,
    selectedPageIndexState: Int,
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
                    if (selectedPageIndexState == 1) {
                        painterResource(id = R.drawable.ic_arrow_left_inactive)
                    } else {
                        painterResource(id = R.drawable.ic_arrow_left_active)
                    }
                } else {
                    if (selectedPageIndexState == itemsSize) {
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
                            if (isBackwardIcon && selectedPageIndexState != 1) {
                                Log.e(
                                    "LOG_TAG",
                                    "clickable 1 -> selectedPageIndex: $selectedPageIndexState   ||   itemsCount: $itemsSize   "
                                )
                                backwardOrForwardItemClicked(selectedPageIndexState - 1)
                            } else if (!isBackwardIcon && selectedPageIndexState != itemsSize) {
                                Log.e(
                                    "LOG_TAG",
                                    "clickable 2 -> selectedPageIndex: $selectedPageIndexState   ||   itemsCount: $itemsSize   "
                                )
                                backwardOrForwardItemClicked(selectedPageIndexState + 1)
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