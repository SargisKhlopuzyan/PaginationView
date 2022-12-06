package com.sargis.khlopuzyan.pagination

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination.ui.theme.PaginationTheme
import com.sargis.khlopuzyan.pagination_view.PaginationStyle
import com.sargis.khlopuzyan.pagination_view.PaginationView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaginationTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {

                        val paginationViewHeight =
                            dimensionResource(id = R.dimen.pagination_View_height)
//                        val paginationViewHeight = 80.dp
                        val paginationViewWidth = 480.dp

                        val pagesCount = 4 //dataList: List<Int>,
                        val selectedPagePosition = 1 //currentPage: Int,
                        val alwaysShowNumber = true
                        val hideViewPagerInOnePageMode = false
                        val animateOnPressEvent = false
                        val paginationStyle = PaginationStyle.PACKED

                        PaginationView(
                            modifier = Modifier
                                .height(paginationViewHeight)
//                                .width(paginationViewWidth)
                                .fillMaxWidth()
                                .background(Color(0x203F51B5)),
                            itemsSize = pagesCount,
                            selectedPageIndex = selectedPagePosition,
                            alwaysShowNumber = alwaysShowNumber,
                            hideViewPagerInOnePageMode = hideViewPagerInOnePageMode,
                            animateOnPressEvent = animateOnPressEvent,
                            paginationStyle = paginationStyle,
                        ) { page ->
                            Log.e("PAGINATION_VIEW", "page $page clicked")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PaginationTheme {
        PaginationView(paginationStyle = PaginationStyle.PACKED) { _ ->

        }
    }
}