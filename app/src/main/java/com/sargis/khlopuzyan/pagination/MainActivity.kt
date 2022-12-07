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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sargis.khlopuzyan.pagination.ui.theme.PaginationTheme
import com.sargis.khlopuzyan.pagination_view.PaginationView
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tests
        // *********************
        val _pagesStateFlow = MutableStateFlow<List<Int>>(listOf())
        val pagesStateFlow = _pagesStateFlow.asStateFlow()

        val _selectedPageStateFlow = MutableStateFlow<Int>(0)
        val selectedPageStateFlow = _selectedPageStateFlow.asStateFlow()

        lifecycleScope.launch {
            delay(2000)
            _pagesStateFlow.value = listOf(1, 2, 3, 4)
            delay(3000)
            _pagesStateFlow.value = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        }

        lifecycleScope.launch {
            delay(3500)
            _selectedPageStateFlow.value = Random.nextInt(2, pagesStateFlow.value.size)
            delay(5000)
            _selectedPageStateFlow.value = Random.nextInt(4, pagesStateFlow.value.size)
        }

        // *********************

        setContent {

            val pages = pagesStateFlow.collectAsState()
            val selectedPage = selectedPageStateFlow.collectAsState()

//          val paginationViewHeight = dimensionResource(id = R.dimen.pagination_View_height)
            val paginationViewHeight = 80.dp
//            val pages = listOf(1, 2, 3, 4)
////            val pages = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
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
                selectedPage = selectedPage.value,
                alwaysShowNumber = alwaysShowNumber,
                hideViewPagerInOnePageMode = hideViewPagerInOnePageMode,
                animateOnPressEvent = animateOnPressEvent,
                paginationViewStyle = paginationViewStyle,
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