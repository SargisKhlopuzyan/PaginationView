package com.sargis.khlopuzyan.pagination

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sargis.khlopuzyan.pagination.ui.theme.PaginationTheme
import com.sargis.khlopuzyan.pagination_view.PaginationView
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import com.sargis.khlopuzyan.pagination_view.data.SwipeDirection
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tests
        // *********************
        val _pagesStateFlow = MutableStateFlow<List<Int>>(listOf())
        val pagesStateFlow = _pagesStateFlow.asStateFlow()

        val _swipeDirectionStateFlow = MutableStateFlow<SwipeDirection>(SwipeDirection.NON)
        val swipeDirectionStateFlow = _swipeDirectionStateFlow.asStateFlow()

        lifecycleScope.launch {
            delay(2000)
            _pagesStateFlow.value = listOf(1, 2, 3, 4)
            delay(3000)
            _pagesStateFlow.value = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        }

        lifecycleScope.launch {
            delay(2500)
            _swipeDirectionStateFlow.value = SwipeDirection.RIGHT
            delay(5000)
            _swipeDirectionStateFlow.value = SwipeDirection.LEFT
        }

        // *********************

        setContent {
            Log.e("LOG_TAG", "SET_CONTENT")

            val pages = pagesStateFlow.collectAsState()
            val swipePagination = swipeDirectionStateFlow.collectAsState()
//          val paginationViewHeight = dimensionResource(id = R.dimen.pagination_View_height)
            val paginationViewHeight = 80.dp
            val alwaysShowNumber = false
            val hideViewPagerInOnePageMode = false
            val animateOnPressEvent = false
            val paginationViewStyle = PaginationViewStyle.SPREAD

            PaginationView(
                modifier = Modifier
                    .height(paginationViewHeight)
                    .fillMaxWidth()
                    .background(Color(0x203F51B5)),
                pagesSize = pages.value.size,
                swipePagination = swipePagination.value,
                alwaysShowNumber = alwaysShowNumber,
                hideViewPagerInOnePageMode = hideViewPagerInOnePageMode,
                animateOnPressEvent = animateOnPressEvent,
                paginationViewStyle = paginationViewStyle
            ) { page ->

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PaginationTheme {
        PaginationView(
            paginationViewStyle = PaginationViewStyle.PACKED,
            pagesSize = 4
        ) { _ ->

        }
    }
}