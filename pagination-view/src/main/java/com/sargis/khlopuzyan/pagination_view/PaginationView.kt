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
    paginationViewInfo: PaginationViewInfo,
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

            val paginationViewUiItemsMaxCount = if (paginationViewInfo.pagesSize == 0) {
                0
            } else if (paginationViewInfo.pagesSize > 5 || paginationViewInfo.alwaysShowNumber) {
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
                pagesSize = paginationViewInfo.pagesSize,
                alwaysShowNumber = paginationViewInfo.alwaysShowNumber,
                paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount,
                selectedPage = paginationViewInfo.selectedPage
            )

            PaginationItemsViewWithBackwardAndForward(
                paginationViewInfo = paginationViewInfo,
                paginationViewUiItems = paginationViewUiItems,
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
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 1,
                //    swipePagination: Int,
                alwaysShowNumber = false,
                hideViewPagerInOnePageMode = true,
                animateOnPressEvent = false,
                paginationViewStyle = PaginationViewStyle.SPREAD,
            )
        ) { _ ->

        }
    }
}