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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.pagination.ui.PaginationUiItemsChainStyle
import com.sargis.khlopuzyan.pagination.ui.PaginationView
import com.sargis.khlopuzyan.pagination.ui.theme.PaginationTheme

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

                        val paginationContainerHeight = 80.dp
                        val paginationContainerWidth = 480.dp

                        val itemsSize: Int = 110 //dataList: List<Int>,
                        val selectedPageIndex: Int = 15 //currentPage: Int,
                        val alwaysShowNumber: Boolean = false

                        val paginationUiItemsChainStyle = PaginationUiItemsChainStyle.PACKED

                        Box(
                            modifier = Modifier
                                .height(paginationContainerHeight)
                                .width(paginationContainerWidth)
                                .background(Color(0x203F51B5)),
                            contentAlignment = Alignment.Center
                        ) {
                            PaginationView(
                                itemsSize = itemsSize,
                                selectedPageIndex = selectedPageIndex,
                                alwaysShowNumber = alwaysShowNumber,
                                paginationUiItemsChainStyle = paginationUiItemsChainStyle,
                            ) { page ->
                                Log.e("PAGINATION_VIEW", "page $page clicked")
                            }
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
        PaginationView(paginationUiItemsChainStyle = PaginationUiItemsChainStyle.PACKED) { page ->
            Log.e("PAGINATION_VIEW", "page $page clicked")
        }
    }
}