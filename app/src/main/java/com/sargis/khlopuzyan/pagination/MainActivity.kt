package com.sargis.khlopuzyan.pagination

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination.ui.theme.PaginationTheme
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import com.sargis.khlopuzyan.pagination_view.PaginationView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//              val paginationViewHeight = dimensionResource(id = R.dimen.pagination_View_height)
                val paginationViewHeight = 80.dp
                val paginationViewWidth = 480.dp

                val pages = listOf(1,2,3,4) //dataList: List<Int>,
                val selectedPagePosition = 1 //currentPage: Int,
                val alwaysShowNumber = true
                val hideViewPagerInOnePageMode = false
                val animateOnPressEvent = false
                val paginationViewStyle = PaginationViewStyle.PACKED

                PaginationView(
                    modifier = Modifier
                        .height(paginationViewHeight)
//                                .width(paginationViewWidth)
                        .fillMaxWidth()
                        .background(Color(0x203F51B5)),
                    pages = pages,
                    selectedPageIndex = selectedPagePosition,
                    alwaysShowNumber = alwaysShowNumber,
                    hideViewPagerInOnePageMode = hideViewPagerInOnePageMode,
                    animateOnPressEvent = animateOnPressEvent,
                    paginationViewStyle = paginationViewStyle,
                ) { page ->
                    Log.e("PAGINATION_VIEW", "page $page clicked")
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PaginationTheme {
        PaginationView(paginationViewStyle = PaginationViewStyle.PACKED) { _ ->

        }
    }
}