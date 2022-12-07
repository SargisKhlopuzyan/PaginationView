package com.sargis.khlopuzyan.pagination_view.util

/**
 * Created by Sargis Khlopuzyan on 12/7/2022.
 */
fun roundUpDivision(num: Int, divisor: Int): Int {
    return (num + divisor - 1) / divisor
}