package com.sargis.khlopuzyan.pagination_view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewDimens
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import com.sargis.khlopuzyan.pagination_view.data.SwipeDirection
import com.sargis.khlopuzyan.pagination_view.pagination.PaginationItemsViewWithBackwardAndForward
import com.sargis.khlopuzyan.pagination_view.util.calculatePaginationViewUiItemsMaxCount
import com.sargis.khlopuzyan.pagination_view.util.initPaginationViewDefaultDimens
import com.sargis.khlopuzyan.pagination_view.util.initPaginationViewUiItems

/**
 * Created by Sargis Khlopuzyan on 11/30/2022.
 */
@Composable
fun PaginationView(
    modifier: Modifier = Modifier
        .height(dimensionResource(id = R.dimen.pagination_View_height))
        .fillMaxWidth(),
    pagesSize: Int,
    selectedPage: Int = 1,
    swipePagination: SwipeDirection = SwipeDirection.NON,
    alwaysShowNumber: Boolean = false,
    hideViewPagerInOnePageMode: Boolean = true,
    animateOnPressEvent: Boolean = false,
    paginationViewStyle: PaginationViewStyle,

    paginationViewDimens: PaginationViewDimens = initPaginationViewDefaultDimens(),

    onPageClicked: (pageNumber: Int) -> Unit
) {

    var paginationViewWidth by remember {
        mutableStateOf(-1f)
    }

    val density = LocalDensity.current.density

    Box(
        modifier = modifier
            .padding(
                horizontal = paginationViewDimens.paginationViewHorizontalSpace,
                vertical = paginationViewDimens.paginationViewVerticalSpace
            )
            .onGloballyPositioned { coordinates ->
                if (paginationViewWidth < 0) {
                    paginationViewWidth = coordinates.size.width / density
                }
            }
    ) {

        if (paginationViewWidth >= 0) {

            val paginationViewUiItemsMaxCount = if (pagesSize == 0) {
                0
            } else if (pagesSize > 5 || alwaysShowNumber) {
                calculatePaginationViewUiItemsMaxCount(
                    containerWidth = paginationViewWidth,
                    paginationViewItemContainerWidth = paginationViewDimens.paginationViewNumericItemDimens.paginationViewItemContainerWidth.value,
                    backwardOrForwardItemContainerWidth = paginationViewDimens.paginationViewBackwardOrForwardItemDimens.backwardOrForwardItemContainerWidth.value,
                    spaceBetweenPaginationViewItems = paginationViewDimens.spaceBetweenPaginationViewItems.value,
                    spaceBetweenBackwardOrForwardItemAndPaginationViewItem = paginationViewDimens.spaceBetweenBackwardOrForwardItemAndPaginationViewItem.value
                )
            } else {
                calculatePaginationViewUiItemsMaxCount(
                    containerWidth = paginationViewWidth,
                    paginationViewItemContainerWidth = paginationViewDimens.paginationViewPillItemDimens.paginationViewItemContainerWidth.value,
                    backwardOrForwardItemContainerWidth = paginationViewDimens.paginationViewBackwardOrForwardItemDimens.backwardOrForwardItemContainerWidth.value,
                    spaceBetweenPaginationViewItems = paginationViewDimens.spaceBetweenPaginationViewItems.value,
                    spaceBetweenBackwardOrForwardItemAndPaginationViewItem = paginationViewDimens.spaceBetweenBackwardOrForwardItemAndPaginationViewItem.value
                )
            }

            val paginationViewUiItems = initPaginationViewUiItems(
                pagesSize = pagesSize,
                alwaysShowNumber = alwaysShowNumber,
                paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount,
                selectedPage = selectedPage
            )

            val paginationViewInfo = PaginationViewInfo(
                pagesSize,
                selectedPage,
                alwaysShowNumber,
                hideViewPagerInOnePageMode,
                animateOnPressEvent,
                paginationViewStyle,
                paginationViewUiItemsMaxCount,
                paginationViewUiItems
            )

            PaginationItemsViewWithBackwardAndForward(
                paginationViewInfo = paginationViewInfo,
                paginationViewDimens = paginationViewDimens,
                onPageClicked = { page ->
                    onPageClicked(page)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaginationViewPreview() {
    MaterialTheme {
        PaginationView(
            paginationViewStyle = PaginationViewStyle.PACKED,
            pagesSize = 4
        ) { _ ->

        }
    }
}