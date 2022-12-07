package com.sargis.khlopuzyan.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Sargis Khlopuzyan on 12/7/2022.
 */
class MainActivityViewModel : ViewModel() {

    val _pages = MutableStateFlow(
        listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21)
    )

    val pages = _pages.asStateFlow()

    private var _paginationViewInfo = MutableStateFlow(
        PaginationViewInfo(
            pagesSize = pages.value.size,
            selectedPage = 2,
            alwaysShowNumber = false,
            hideViewPagerInOnePageMode = true,
            animateOnPressEvent = false,
            paginationViewStyle = PaginationViewStyle.SPREAD
        )
    )

    var paginationViewInfo = _paginationViewInfo.asStateFlow()

    init {
        viewModelScope.launch {
            delay(4000)
            _pages.value = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
            _paginationViewInfo.value = paginationViewInfo.value.copy(pagesSize = pages.value.size)

            delay(3000)
            _paginationViewInfo.value = paginationViewInfo.value.copy(selectedPage = 3)
        }
    }

    fun updatePaginationViewInfo(selectedPage: Int) {
        _paginationViewInfo.value = paginationViewInfo.value.copy(selectedPage = selectedPage)
    }

}