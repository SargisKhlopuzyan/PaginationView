package com.sargis.khlopuzyan.pagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.sargis.khlopuzyan.pagination.ui.theme.PaginationTheme
import com.sargis.khlopuzyan.pagination_view.PaginationView
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setContent {
            val paginationViewInfo = viewModel.paginationViewInfo.collectAsState()
            PaginationView(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.pagination_View_height))
                    .fillMaxWidth()
                    .background(Color(0x203F51B5)),
                paginationViewInfo = paginationViewInfo.value
            ) { page ->
                viewModel.updatePaginationViewInfo(page)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PaginationTheme {
        PaginationView(
            paginationViewInfo = PaginationViewInfo(
                pagesSize = 4,
                selectedPage = 1,
                alwaysShowNumber = false,
                hideViewPagerInOnePageMode = true,
                animateOnPressEvent = false,
                paginationViewStyle = PaginationViewStyle.SPREAD
            )
        ) { _ ->

        }
    }
}