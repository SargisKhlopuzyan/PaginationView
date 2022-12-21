package com.mudita.compact.pagination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mudita.compact.pagination.data.PaginationViewInfo
import com.mudita.compact.pagination.data.PaginationViewResources
import com.mudita.compact.pagination.pagination.PaginationItemsViewWithBackwardAndForward
import com.mudita.compact.pagination.util.calculatePaginationViewUiItemsMaxCount
import com.mudita.compact.pagination.util.initPaginationViewDefaultResources
import com.mudita.compact.pagination.util.initPaginationViewUiItems

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

    val paginationViewModifier =
        if (paginationViewInfo.paginationViewItemsMaxCount == null || paginationViewWidth < 0) {
            modifier
                .padding(
                    horizontal = paginationViewResources.paginationViewHorizontalSpace,
                    vertical = paginationViewResources.paginationViewVerticalSpace
                )
                .onGloballyPositioned { coordinates ->
                    if (paginationViewInfo.paginationViewItemsMaxCount == null && paginationViewWidth < 0) {
                        paginationViewWidth = coordinates.size.width / density
                    }
                }
        } else {
            modifier.padding(
                horizontal = paginationViewResources.paginationViewHorizontalSpace,
                vertical = paginationViewResources.paginationViewVerticalSpace
            )
        }

    Box(modifier = paginationViewModifier) {
        if (paginationViewInfo.paginationViewItemsMaxCount != null || paginationViewWidth >= 0) {
            val paginationViewUiItemsMaxCount =
                if (paginationViewInfo.paginationViewItemsMaxCount == null) {
                    if (paginationViewInfo.isAlwaysNumeric || paginationViewInfo.pagesSize > paginationViewInfo.paginationViewPillItemsMaxCount) {
                        calculatePaginationViewUiItemsMaxCount(
                            containerWidth = paginationViewWidth,
                            paginationViewItemContainerWidth = paginationViewResources.numericItemResources.itemContainerWidth.value,
                            backwardOrForwardItemContainerWidth = paginationViewResources.backwardOrForwardItemResources.itemContainerWidth.value,
                            spaceBetweenPaginationViewItems = paginationViewResources.spaceBetweenPaginationViewItems.value,
                            spaceBetweenBackwardOrForwardItemAndPaginationViewItem = paginationViewResources.spaceBetweenBackwardOrForwardItemAndPaginationViewItem.value
                        )
                    } else {
                        calculatePaginationViewUiItemsMaxCount(
                            containerWidth = paginationViewWidth,
                            paginationViewItemContainerWidth = paginationViewResources.pillItemResources.itemContainerWidth.value,
                            backwardOrForwardItemContainerWidth = paginationViewResources.backwardOrForwardItemResources.itemContainerWidth.value,
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
                paginationViewWidth = paginationViewWidth,
                paginationViewInfo = paginationViewInfo,
                paginationViewUiItemsMaxCount = paginationViewUiItemsMaxCount
            )

            PaginationItemsViewWithBackwardAndForward(paginationViewInfo = paginationViewInfo,
                paginationViewUiItems = paginationViewUiItems,
                paginationViewResources = paginationViewResources,
                onPageClicked = { page ->
                    onPageClicked(page)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaginationViewPreview() {
    MaterialTheme {
        PaginationView(
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4, selectedPage = 1
            )
        ) {}
    }
}