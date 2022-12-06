package com.sargis.khlopuzyan.pagination_view.util

import com.sargis.khlopuzyan.pagination_view.data.PaginationViewDotUiItem
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewNumericUiItem
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewPillUiItem
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewUiItem

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
fun calculatePaginationViewUiItemsMaxCount(
    containerWidth: Float,
    paginationViewItemContainerWidth: Float,
    backwardOrForwardItemContainerWidth: Float,
    spaceBetweenPaginationViewItems: Float,
    spaceBetweenBackwardOrForwardItemAndPaginationViewItem: Float
): Int {

    val paginationViewItemsSpace =
        containerWidth - 2 * (backwardOrForwardItemContainerWidth + spaceBetweenBackwardOrForwardItemAndPaginationViewItem)
    val maxCount =
        (paginationViewItemsSpace + spaceBetweenPaginationViewItems) / (paginationViewItemContainerWidth + spaceBetweenPaginationViewItems)

    return maxCount.toInt()
}

fun initPaginationViewUiItems(
    pagesSize: Int,
    alwaysShowNumber: Boolean,
    paginationViewUiItemsMaxCount: Int,
    selectedPageIndex: Int
): List<PaginationViewUiItem> {

    val paginationViewUiItems = mutableListOf<PaginationViewUiItem>()

    if (pagesSize > 5 || alwaysShowNumber) {
        when {
            pagesSize <= paginationViewUiItemsMaxCount -> {

                (1..pagesSize).forEach {

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = it,
                            uiPageIndex = it,
                            isSelected = it == selectedPageIndex
                        )
                    )

                }

            }
            else -> {

                // 1 2 3 4 5 6 |7| 8 9 10 11 ... 20
                if (selectedPageIndex <= roundUpDivision(paginationViewUiItemsMaxCount, 2)) {

                    (1..paginationViewUiItemsMaxCount - 2).forEach {

                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it,
                                uiPageIndex = it,
                                isSelected = it == selectedPageIndex
                            )
                        )

                    }

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(uiPageIndex = paginationViewUiItemsMaxCount - 1)
                    )

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = pagesSize,
                            uiPageIndex = paginationViewUiItemsMaxCount,
                            isSelected = false
                        )
                    )

                    // 1 ... 10 11 12 13 |14| 15 16 17 18 19 20
                } else if (pagesSize - selectedPageIndex < roundUpDivision(paginationViewUiItemsMaxCount, 2)) {

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(page = 1, uiPageIndex = 1, isSelected = false)
                    )

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(uiPageIndex = 2)
                    )

                    val diff = pagesSize - paginationViewUiItemsMaxCount

                    (3..paginationViewUiItemsMaxCount).forEach {

                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it + diff,
                                uiPageIndex = it,
                                isSelected = it + diff == selectedPageIndex
                            )
                        )

                    }

                    // 1 ... 6 7 8 9 |10| 11 12 13 14 ... 20
                } else {

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(page = 1, uiPageIndex = 1, isSelected = false)
                    )

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(uiPageIndex = 2)
                    )

                    val diff = selectedPageIndex - paginationViewUiItemsMaxCount / 2 - 1

                    (3..paginationViewUiItemsMaxCount - 2).forEach {

                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it + diff,
                                uiPageIndex = it,
                                isSelected = it + diff == selectedPageIndex
                            )
                        )

                    }

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(uiPageIndex = paginationViewUiItemsMaxCount - 1)
                    )

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = pagesSize,
                            uiPageIndex = paginationViewUiItemsMaxCount,
                            isSelected = false
                        )
                    )

                }
            }
        }
    } else {
        (1..if (paginationViewUiItemsMaxCount > pagesSize) pagesSize else paginationViewUiItemsMaxCount).forEach {

            paginationViewUiItems.add(
                PaginationViewPillUiItem(
                    page = it,
                    uiPageIndex = it,
                    isSelected = it == selectedPageIndex
                )
            )
        }
    }

    return paginationViewUiItems
}

private fun roundUpDivision(num: Int, divisor: Int): Int {
    return (num + divisor - 1) / divisor
}