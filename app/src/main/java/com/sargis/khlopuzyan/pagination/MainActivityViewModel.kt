package com.sargis.khlopuzyan.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Sargis Khlopuzyan on 12/7/2022.
 */
class MainActivityViewModel : ViewModel() {

    // Demo
    private var _paginationViewInfo = MutableStateFlow(
        PaginationViewInfo(
            pagesSize = 8,
            selectedPage = 3,
            paginationViewItemsMaxCount = null,
            paginationViewPillItemsMaxCount = 10
        )
    )
    var paginationViewInfo = _paginationViewInfo.asStateFlow()

    init {
        viewModelScope.launch {
            delay(4000)
            _paginationViewInfo.value =
                paginationViewInfo.value.copy(pagesSize = 15, selectedPage = 8)
            delay(3000)
            _paginationViewInfo.value =
                paginationViewInfo.value.copy(pagesSize = 88, selectedPage = 33)
            delay(3000)
            _paginationViewInfo.value =
                paginationViewInfo.value.copy(pagesSize = 40, selectedPage = 1)
        }
    }

    fun updatePaginationViewInfo(selectedPage: Int) {
        _paginationViewInfo.value = paginationViewInfo.value.copy(selectedPage = selectedPage)
    }

}