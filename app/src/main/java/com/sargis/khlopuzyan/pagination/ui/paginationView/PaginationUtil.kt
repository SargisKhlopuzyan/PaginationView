package com.sargis.khlopuzyan.pagination.ui.paginationView

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
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