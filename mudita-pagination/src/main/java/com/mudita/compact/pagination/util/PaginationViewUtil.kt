package com.mudita.compact.pagination.util

import com.mudita.compact.pagination.data.PaginationViewDotUiItem
import com.mudita.compact.pagination.data.PaginationViewInfo
import com.mudita.compact.pagination.data.PaginationViewNumericUiItem
import com.mudita.compact.pagination.data.PaginationViewPillUiItem
import com.mudita.compact.pagination.data.PaginationViewUiItem

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
    paginationViewWidth: Float,
    paginationViewInfo: PaginationViewInfo,
    paginationViewUiItemsMaxCount: Int
): List<PaginationViewUiItem> {

    val paginationViewUiItems = mutableListOf<PaginationViewUiItem>()

    if (paginationViewInfo.pagesSize == 0) {
        return paginationViewUiItems
    }

    if (paginationViewInfo.isAlwaysNumeric || paginationViewInfo.pagesSize > paginationViewInfo.paginationViewPillItemsMaxCount) {
        when {
            paginationViewInfo.pagesSize <= paginationViewUiItemsMaxCount || paginationViewWidth == 0f -> {
                (1..paginationViewInfo.pagesSize).forEach {
                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = it,
                            paginationViewUiItemIndex = it
                        )
                    )
                }
            }
            else -> {

                // 1 2 3 4 5 6 |7| 8 9 10 11 ... 20
                if (paginationViewInfo.selectedPage <= roundUpDivision(
                        paginationViewUiItemsMaxCount,
                        2
                    )
                ) {
                    (1..paginationViewUiItemsMaxCount - 2).forEach {
                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it,
                                paginationViewUiItemIndex = it,
                            )
                        )
                    }

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(paginationViewUiItemIndex = paginationViewUiItemsMaxCount - 1)
                    )

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = paginationViewInfo.pagesSize,
                            paginationViewUiItemIndex = paginationViewUiItemsMaxCount,
                        )
                    )

                    // 1 ... 10 11 12 13 |14| 15 16 17 18 19 20
                } else if (paginationViewInfo.pagesSize - paginationViewInfo.selectedPage < roundUpDivision(
                        paginationViewUiItemsMaxCount,
                        2
                    )
                ) {

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(page = 1, paginationViewUiItemIndex = 1)
                    )

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(paginationViewUiItemIndex = 2)
                    )

                    val diff = paginationViewInfo.pagesSize - paginationViewUiItemsMaxCount

                    (3..paginationViewUiItemsMaxCount).forEach {
                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it + diff,
                                paginationViewUiItemIndex = it
                            )
                        )
                    }

                    // 1 ... 6 7 8 9 |10| 11 12 13 14 ... 20
                } else {

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(page = 1, paginationViewUiItemIndex = 1)
                    )

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(paginationViewUiItemIndex = 2)
                    )

                    val diff =
                        paginationViewInfo.selectedPage - paginationViewUiItemsMaxCount / 2 - 1

                    (3..paginationViewUiItemsMaxCount - 2).forEach {
                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it + diff,
                                paginationViewUiItemIndex = it
                            )
                        )
                    }

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(paginationViewUiItemIndex = paginationViewUiItemsMaxCount - 1)
                    )

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = paginationViewInfo.pagesSize,
                            paginationViewUiItemIndex = paginationViewUiItemsMaxCount
                        )
                    )
                }
            }
        }
    } else {

        val size =
            if (paginationViewUiItemsMaxCount > paginationViewInfo.pagesSize || paginationViewWidth == 0f) {
                paginationViewInfo.pagesSize
            } else {
                paginationViewUiItemsMaxCount
            }

        (1..size).forEach {
            paginationViewUiItems.add(
                PaginationViewPillUiItem(
                    page = it,
                    paginationViewUiItemIndex = it
                )
            )
        }
    }

    return paginationViewUiItems
}