package com.mudita.compact.pagination.preview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mudita.compact.pagination.PaginationView
import com.mudita.compact.pagination.R
import com.mudita.compact.pagination.data.PaginationViewInfo
import com.mudita.compact.pagination.theme.PaginationTheme

/**
 * Created by Sargis Khlopuzyan on 12/8/2022.
 */
@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewNumericPreview1() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 88,
                selectedPage = 1,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewNumericPreview2() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 88,
                selectedPage = 3,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewNumericPreview3() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 88,
                selectedPage = 55,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewNumericPreview4() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 88,
                selectedPage = 87,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewNumericPreview5() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 88,
                selectedPage = 88,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewPillPreview1() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 1,
                isAlwaysNumeric = false,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewPillPreview2() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 3,
                isAlwaysNumeric = false,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewPillPreview3() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 4,
                isAlwaysNumeric = false,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewAlwaysNumericPreview1() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 1,
                isAlwaysNumeric = true,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewAlwaysNumericPreview2() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 3,
                isAlwaysNumeric = true,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    heightDp = 80
)
@Composable
fun PaginationViewAlwaysNumericPreview3() {
    PaginationTheme {
        PaginationView(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.pagination_View_height))
                .fillMaxWidth(),
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 4,
                isAlwaysNumeric = true,
                paginationViewItemsMaxCount = 7,
                paginationViewPillItemsMaxCount = 5
            )
        ) {}
    }
}