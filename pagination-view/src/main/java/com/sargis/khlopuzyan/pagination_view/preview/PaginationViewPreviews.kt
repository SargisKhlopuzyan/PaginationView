package com.sargis.khlopuzyan.pagination_view.preview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.sargis.khlopuzyan.pagination_view.PaginationView
import com.sargis.khlopuzyan.pagination_view.R
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo
import com.sargis.khlopuzyan.pagination_view.theme.PaginationTheme

/**
 * Created by Sargis Khlopuzyan on 12/8/2022.
 */
@Preview
@Composable
fun PaginationViewWithLessItemsWithPillItemsPreview() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 8,
                selectedPage = 3,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {

        }
    }
}

@Preview
@Composable
fun PaginationViewWithMoreItemsWithPillItemsPreview() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 3,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {

        }
    }
}