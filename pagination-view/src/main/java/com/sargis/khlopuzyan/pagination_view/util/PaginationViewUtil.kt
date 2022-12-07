package com.sargis.khlopuzyan.pagination_view.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.sargis.khlopuzyan.pagination_view.R
import com.sargis.khlopuzyan.pagination_view.data.*

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
    selectedPage: Int
): List<PaginationViewUiItem> {

    val paginationViewUiItems = mutableListOf<PaginationViewUiItem>()

    if (pagesSize == 0) {
        return paginationViewUiItems
    }

    if (pagesSize > 5 || alwaysShowNumber) {
        when {
            pagesSize <= paginationViewUiItemsMaxCount -> {

                (1..pagesSize).forEach {

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = it,
                            paginationViewUiItemIndex = it,
                            isSelected = it == selectedPage
                        )
                    )

                }

            }
            else -> {

                // 1 2 3 4 5 6 |7| 8 9 10 11 ... 20
                if (selectedPage <= roundUpDivision(paginationViewUiItemsMaxCount, 2)) {

                    (1..paginationViewUiItemsMaxCount - 2).forEach {

                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it,
                                paginationViewUiItemIndex = it,
                                isSelected = it == selectedPage
                            )
                        )

                    }

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(paginationViewUiItemIndex = paginationViewUiItemsMaxCount - 1)
                    )

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = pagesSize,
                            paginationViewUiItemIndex = paginationViewUiItemsMaxCount,
                            isSelected = false
                        )
                    )

                    // 1 ... 10 11 12 13 |14| 15 16 17 18 19 20
                } else if (pagesSize - selectedPage < roundUpDivision(paginationViewUiItemsMaxCount, 2)) {

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(page = 1, paginationViewUiItemIndex = 1, isSelected = false)
                    )

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(paginationViewUiItemIndex = 2)
                    )

                    val diff = pagesSize - paginationViewUiItemsMaxCount

                    (3..paginationViewUiItemsMaxCount).forEach {

                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it + diff,
                                paginationViewUiItemIndex = it,
                                isSelected = it + diff == selectedPage
                            )
                        )

                    }

                    // 1 ... 6 7 8 9 |10| 11 12 13 14 ... 20
                } else {

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(page = 1, paginationViewUiItemIndex = 1, isSelected = false)
                    )

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(paginationViewUiItemIndex = 2)
                    )

                    val diff = selectedPage - paginationViewUiItemsMaxCount / 2 - 1

                    (3..paginationViewUiItemsMaxCount - 2).forEach {

                        paginationViewUiItems.add(
                            PaginationViewNumericUiItem(
                                page = it + diff,
                                paginationViewUiItemIndex = it,
                                isSelected = it + diff == selectedPage
                            )
                        )

                    }

                    paginationViewUiItems.add(
                        PaginationViewDotUiItem(paginationViewUiItemIndex = paginationViewUiItemsMaxCount - 1)
                    )

                    paginationViewUiItems.add(
                        PaginationViewNumericUiItem(
                            page = pagesSize,
                            paginationViewUiItemIndex = paginationViewUiItemsMaxCount,
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
                    paginationViewUiItemIndex = it,
                    isSelected = it == selectedPage
                )
            )
        }
    }

    return paginationViewUiItems
}

@Composable
fun initPaginationViewDefaultDimens() = PaginationViewDimens(

    // PAGINATION VIEW
    paginationViewHeight = dimensionResource(id = R.dimen.pagination_View_height),
    paginationViewHorizontalSpace = dimensionResource(id = R.dimen.pagination_view_horizontal_space),
    paginationViewVerticalSpace = dimensionResource(id = R.dimen.pagination_view_vertical_space),
    spaceBetweenPaginationViewItems = dimensionResource(id = R.dimen.space_between_pagination_view_items),
    spaceBetweenBackwardOrForwardItemAndPaginationViewItem = dimensionResource(id = R.dimen.space_between_backward_or_forward_item_and_pagination_view_item),

    // BACKWARD or FORWARD ITEM
    paginationViewBackwardOrForwardItemDimens = PaginationViewBackwardOrForwardItemDimens(
        backwardOrForwardItemContainerWidth = dimensionResource(id = R.dimen.backward_or_forward_item_container_width),
        backwardOrForwardItemContainerHeight = dimensionResource(id = R.dimen.backward_or_forward_item_container_height),

        backwardOrForwardItemWidth = dimensionResource(id = R.dimen.backward_or_forward_item_width),
        backwardOrForwardItemHeight = dimensionResource(id = R.dimen.backward_or_forward_item_height),

        backwardOrForwardItemCornerRadius = dimensionResource(id = R.dimen.backward_or_forward_item_corner_radius)
    ),

    // PAGINATION VIEW NUMERIC ITEM
    paginationViewNumericItemDimens = PaginationViewItemDimens(
        paginationViewItemWidth = dimensionResource(id = R.dimen.pagination_view_numeric_item_width),
        paginationViewItemHeight = dimensionResource(id = R.dimen.pagination_view_numeric_item_height),

        paginationViewItemContainerWidth = dimensionResource(id = R.dimen.pagination_view_numeric_item_container_width),
        paginationViewItemContainerHeight = dimensionResource(id = R.dimen.pagination_view_numeric_item_container_height),

        paginationViewItemCornerRadius = dimensionResource(id = R.dimen.pagination_view_numeric_item_corner_radius),
        paginationViewItemBorderStroke = dimensionResource(id = R.dimen.pagination_view_numeric_item_border_stroke),

        spaceBetweenPaginationViewItems = dimensionResource(id = R.dimen.space_between_pagination_view_items)
    ),

    // PAGINATION VIEW DOT ITEM
    paginationViewDotItemDimens = PaginationViewItemDimens(
        paginationViewItemWidth = dimensionResource(id = R.dimen.pagination_view_numeric_item_width),
        paginationViewItemHeight = dimensionResource(id = R.dimen.pagination_view_numeric_item_height),

        paginationViewItemContainerWidth = dimensionResource(id = R.dimen.pagination_view_numeric_item_container_width),
        paginationViewItemContainerHeight = dimensionResource(id = R.dimen.pagination_view_numeric_item_container_height),

        paginationViewItemCornerRadius = dimensionResource(id = R.dimen.pagination_view_numeric_item_corner_radius),
        paginationViewItemBorderStroke = dimensionResource(id = R.dimen.pagination_view_numeric_item_border_stroke),

        spaceBetweenPaginationViewItems = dimensionResource(id = R.dimen.space_between_pagination_view_items)
    ),

    // PAGINATION VIEW PILL ITEM
    paginationViewPillItemDimens = PaginationViewItemDimens(
        paginationViewItemContainerWidth = dimensionResource(id = R.dimen.pagination_view_pill_item_container_width),
        paginationViewItemContainerHeight = dimensionResource(id = R.dimen.pagination_view_pill_item_container_height),

        paginationViewItemWidth = dimensionResource(id = R.dimen.pagination_view_pill_item_width),
        paginationViewItemHeight = dimensionResource(id = R.dimen.pagination_view_pill_item_height),

        paginationViewItemCornerRadius = dimensionResource(id = R.dimen.pagination_view_pill_item_corner_radius),
        paginationViewItemBorderStroke = dimensionResource(id = R.dimen.pagination_view_pill_item_border_stroke),

        spaceBetweenPaginationViewItems = dimensionResource(id = R.dimen.space_between_pagination_view_items)
    )
)

