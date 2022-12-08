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
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewResources
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo
import com.sargis.khlopuzyan.pagination_view.pagination.PaginationItemsViewWithBackwardAndForward
import com.sargis.khlopuzyan.pagination_view.util.calculatePaginationViewUiItemsMaxCount
import com.sargis.khlopuzyan.pagination_view.util.initPaginationViewDefaultResources
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
    paginationViewResources: PaginationViewResources = initPaginationViewDefaultResources(),
    onPageClicked: (pageNumber: Int) -> Unit
) {

    var paginationViewWidth by remember {
        mutableStateOf(-1f)
    }

    val density = LocalDensity.current.density

    Box(
        modifier = modifier
            .padding(
                horizontal = paginationViewResources.paginationViewHorizontalSpace,
                vertical = paginationViewResources.paginationViewVerticalSpace
            )
            .onGloballyPositioned { coordinates ->
                if (paginationViewInfo.paginationViewItemsMaxCount == null && paginationViewWidth < 0) {
                    paginationViewWidth = coordinates.size.width / density
                }
            }
    ) {
        if (paginationViewInfo.paginationViewItemsMaxCount != null || paginationViewWidth >= 0) {
            val paginationViewUiItemsMaxCount =
                if (paginationViewInfo.paginationViewItemsMaxCount == null) {
                    if (paginationViewInfo.isAlwaysNumeric || paginationViewInfo.pagesSize > paginationViewInfo.paginationViewPillItemsMaxCount) {
                        calculatePaginationViewUiItemsMaxCount(
                            containerWidth = paginationViewWidth,
                            paginationViewItemContainerWidth = paginationViewResources.paginationViewNumericItemResources.paginationViewItemContainerWidth.value,
                            backwardOrForwardItemContainerWidth = paginationViewResources.paginationViewBackwardOrForwardItemResources.backwardOrForwardItemContainerWidth.value,
                            spaceBetweenPaginationViewItems = paginationViewResources.spaceBetweenPaginationViewItems.value,
                            spaceBetweenBackwardOrForwardItemAndPaginationViewItem = paginationViewResources.spaceBetweenBackwardOrForwardItemAndPaginationViewItem.value
                        )
                    } else {
                        calculatePaginationViewUiItemsMaxCount(
                            containerWidth = paginationViewWidth,
                            paginationViewItemContainerWidth = paginationViewResources.paginationViewPillItemResources.paginationViewItemContainerWidth.value,
                            backwardOrForwardItemContainerWidth = paginationViewResources.paginationViewBackwardOrForwardItemResources.backwardOrForwardItemContainerWidth.value,
                            spaceBetweenPaginationViewItems = paginationViewResources.spaceBetweenPaginationViewItems.value,
                            spaceBetweenBackwardOrForwardItemAndPaginationViewItem = paginationViewResources.spaceBetweenBackwardOrForwardItemAndPaginationViewItem.value
                        )
                    }
                } else {
                    if (paginationViewInfo.isAlwaysNumeric || paginationViewInfo.pagesSize > paginationViewInfo.paginationViewPillItemsMaxCount) {
                        paginationViewInfo.paginationViewItemsMaxCount
                    } else {
                        paginationViewInfo.paginationViewPillItemsMaxCount
                    }
                }

            val paginationViewUiItems = initPaginationViewUiItems(
                paginationViewInfo = paginationViewInfo,
                paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount
            )

            PaginationItemsViewWithBackwardAndForward(
                paginationViewInfo = paginationViewInfo,
                paginationViewUiItems = paginationViewUiItems,
                paginationViewResources = paginationViewResources,
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
                selectedPage = 1
            )
        ) {

        }
    }
}