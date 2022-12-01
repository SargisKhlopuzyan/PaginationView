package com.sargis.khlopuzyan.pagination.ui

import android.icu.lang.UCharacter.NumericType
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sargis.khlopuzyan.pagination.R
import com.sargis.khlopuzyan.pagination.ui.theme.NumericPaginationItemText

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */

@Preview(showBackground = true)
@Composable
fun NumericPaginationGenericPreview() {
    MaterialTheme {
        NumericPaginationGeneric()
    }
}

@Composable
fun NumericPaginationGeneric() {

    val selectedPageIndex = 13
    val maxPagesCount = 7
    val pagesSize = 30

    val items by remember {
        mutableStateOf(
            (1..pagesSize).map {
                GenericNumericPageItem(
                    page = it,
                    pageIndex = it,
                    isSelected = it == selectedPageIndex
                )
            }
        )
    }

    val paginationItems = initPaginationItems(
        items,
        maxPagesCount = maxPagesCount,
        selectedPageIndex = selectedPageIndex
    )

    var paginationItemsState by remember {
        mutableStateOf(paginationItems)
    }

    val paginationCount =
        calculatePaginationItemsCount(items = items, maxPagesCount = maxPagesCount)

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .height(40.dp)
    ) {
        items(count = paginationCount) {
            when (val pageItem: GenericPageItem = paginationItemsState[it]) {
                is GenericNumericPageItem -> {
                    PageNumericItemCompose(
                        numericPageItem = pageItem,
                        pageItemsSpace = 10.dp,
                        pagesCount = items.size
                    ) { page ->

                        val newPaginationItems = initPaginationItems(
                            items,
                            maxPagesCount = maxPagesCount,
                            selectedPageIndex = page
                        )

                        paginationItemsState = newPaginationItems
                    }
                }
                is GenericDotPageItem -> {
                    PageDotItemCompose(pageItemsSpace = 10.dp)
                }
            }
        }
    }
}

fun calculatePaginationItemsCount(items: List<GenericPageItem>, maxPagesCount: Int): Int {
    return when {
        items.size <= maxPagesCount -> items.size
        else -> maxPagesCount
    }
}

fun initPaginationItems(
    items: List<GenericPageItem>,
    maxPagesCount: Int,
    selectedPageIndex: Int
): List<GenericPageItem> {

    val paginationItems = mutableListOf<GenericPageItem>()

    when {
        items.size <= maxPagesCount -> {

            (1..items.size).forEach {

                paginationItems.add(
                    GenericNumericPageItem(
                        page = it,
                        pageIndex = it,
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
                        GenericNumericPageItem(
                            page = it,
                            pageIndex = it,
                            isSelected = it == selectedPageIndex
                        )
                    )

                }

                paginationItems.add(
                    GenericDotPageItem(pageIndex = maxPagesCount - 1)
                )

                paginationItems.add(
                    GenericNumericPageItem(
                        page = items.size,
                        pageIndex = maxPagesCount,
                        isSelected = false
                    )
                )

                // 1 ... 10 11 12 13 |14| 15 16 17 18 19 20
            } else if (items.size - selectedPageIndex < roundUpDivision(maxPagesCount, 2)) {

                paginationItems.add(
                    GenericNumericPageItem(page = 1, pageIndex = 1, isSelected = false)
                )

                paginationItems.add(
                    GenericDotPageItem(pageIndex = 2)
                )

                val diff = items.size - maxPagesCount

                (3..maxPagesCount).forEach {

                    paginationItems.add(
                        GenericNumericPageItem(
                            page = it + diff,
                            pageIndex = it,
                            isSelected = it + diff == selectedPageIndex
                        )
                    )

                }

                // 1 ... 6 7 8 9 |10| 11 12 13 14 ... 20
            } else {

                paginationItems.add(
                    GenericNumericPageItem(page = 1, pageIndex = 1, isSelected = false)
                )

                paginationItems.add(
                    GenericDotPageItem(pageIndex = 2)
                )

                val diff = selectedPageIndex - maxPagesCount / 2 - 1

                (3..maxPagesCount - 2).forEach {

                    paginationItems.add(
                        GenericNumericPageItem(
                            page = it + diff,
                            pageIndex = it,
                            isSelected = it + diff == selectedPageIndex
                        )
                    )

                }

                paginationItems.add(
                    GenericDotPageItem(pageIndex = maxPagesCount - 1)
                )

                paginationItems.add(
                    GenericNumericPageItem(
                        page = items.size,
                        pageIndex = maxPagesCount,
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
    pageItemsSpace: Dp,
    numericPageItem: GenericNumericPageItem,
    pagesCount: Int,
    pageClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        if (pagesCount > 1 && numericPageItem.page != 1) {
            Spacer(modifier = Modifier.width(pageItemsSpace / 2))
        }

        Box(
            modifier = modifier
                .height(dimensionResource(id = R.dimen.pagination_item_container_height))
                .width(dimensionResource(id = R.dimen.pagination_item_container_height)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(dimensionResource(id = R.dimen.pagination_item_height))
                    .width(dimensionResource(id = R.dimen.pagination_item_height))
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

        if (pagesCount > 1 && numericPageItem.page != pagesCount) {
            Spacer(modifier = Modifier.width(pageItemsSpace / 2))
        }

    }

}

@Composable
fun PageDotItemCompose(
    modifier: Modifier = Modifier,
    pageItemsSpace: Dp
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        Spacer(modifier = Modifier.width(pageItemsSpace / 2))

        Box(
            modifier = modifier
                .height(dimensionResource(id = R.dimen.pagination_item_container_height))
                .width(dimensionResource(id = R.dimen.pagination_item_container_height)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .height(dimensionResource(id = R.dimen.pagination_item_height))
                    .width(dimensionResource(id = R.dimen.pagination_item_height))
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "...",
                    style = NumericPaginationItemText,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.width(pageItemsSpace / 2))
    }

}
