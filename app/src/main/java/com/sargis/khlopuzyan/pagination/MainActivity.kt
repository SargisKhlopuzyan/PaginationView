package com.sargis.khlopuzyan.pagination

import android.os.Bundle
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
import com.sargis.khlopuzyan.pagination.ui.Pagination
import com.sargis.khlopuzyan.pagination.ui.PaginationUiItemsChainStyle
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

                        Box(
                            modifier = Modifier
                                .height(40.dp)
//                                .width(widthDps.dp)
                                .width((2020 / 2.75).dp)
                                .background(Color.Green),
                            contentAlignment = Alignment.Center
                        ) {

                        }

                        Box(
                            modifier = Modifier
                                .height(40.dp)
//                                .width((2020 / 2.75).dp),
                                .width(200.dp),
//                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Pagination(PaginationUiItemsChainStyle.PACKED)
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
        Pagination(PaginationUiItemsChainStyle.PACKED)
    }
}