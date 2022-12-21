package com.mudita.compact.pagination

import androidx.lifecycle.ViewModel
import com.mudita.compact.pagination.data.PaginationViewInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by Sargis Khlopuzyan on 12/7/2022.
 */
class MainActivityViewModel : ViewModel() {

    // Demo
    private var _paginationViewInfo = MutableStateFlow(
        PaginationViewInfo(
            pagesSize = 1222,
            isAlwaysNumeric = true,
            paginationViewItemsMaxCount = null
        )
    )
    var paginationViewInfo = _paginationViewInfo.asStateFlow()

    init {
//        viewModelScope.launch {
//            delay(4000)
//            _paginationViewInfo.value =
//                paginationViewInfo.value.copy(pagesSize = 15, selectedPage = 8)
//            delay(3000)
//            _paginationViewInfo.value =
//                paginationViewInfo.value.copy(pagesSize = 88, selectedPage = 33)
//            delay(3000)
//            _paginationViewInfo.value =
//                paginationViewInfo.value.copy(pagesSize = 40, selectedPage = 1)
//        }
    }

    fun updatePaginationViewInfo(selectedPage: Int) {
        _paginationViewInfo.value = paginationViewInfo.value.copy(selectedPage = selectedPage)
    }

}