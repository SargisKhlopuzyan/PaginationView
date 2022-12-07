package com.sargis.khlopuzyan.pagination_view.backwardOrForwardItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sargis.khlopuzyan.pagination_view.R
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewBackwardOrForwardItemDimens
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun BackwardOrForwardItemCompose(
    paginationViewInfo: PaginationViewInfo,
    isBackwardIcon: Boolean,
    paginationViewBackwardOrForwardItemDimens: PaginationViewBackwardOrForwardItemDimens,
    backwardOrForwardItemClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {

        Box(
            modifier = Modifier
                .height(paginationViewBackwardOrForwardItemDimens.backwardOrForwardItemContainerHeight)
                .width(paginationViewBackwardOrForwardItemDimens.backwardOrForwardItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(paginationViewBackwardOrForwardItemDimens.backwardOrForwardItemHeight)
                    .width(paginationViewBackwardOrForwardItemDimens.backwardOrForwardItemWidth)
                    .clip(
                        shape = RoundedCornerShape(paginationViewBackwardOrForwardItemDimens.backwardOrForwardItemCornerRadius)
                    )
            ) {

                val iconPainter = if (isBackwardIcon) {
                    if (paginationViewInfo.selectedPage == 1) {
                        painterResource(id = R.drawable.ic_arrow_left_inactive)
                    } else {
                        painterResource(id = R.drawable.ic_arrow_left_active)
                    }
                } else {
                    if (paginationViewInfo.selectedPage == paginationViewInfo.pagesSize) {
                        painterResource(id = R.drawable.ic_arrow_right_inactive)
                    } else {
                        painterResource(id = R.drawable.ic_arrow_right_active)
                    }
                }

                Image(
                    painter = iconPainter,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .matchParentSize()
                        .clickable(
                            indication = if (!paginationViewInfo.animateOnPressEvent) null else LocalIndication.current,
                            interactionSource = remember {
                                MutableInteractionSource()
                            }
                        ) {
                            if (isBackwardIcon && paginationViewInfo.selectedPage != 1) {
                                backwardOrForwardItemClicked(paginationViewInfo.selectedPage - 1)
                            } else if (!isBackwardIcon && paginationViewInfo.selectedPage != paginationViewInfo.pagesSize) {
                                backwardOrForwardItemClicked(paginationViewInfo.selectedPage + 1)
                            }
                        }
                )
            }
        }
    }

}