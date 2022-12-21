package com.mudita.compact.pagination.backwardOrForwardItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.mudita.compact.pagination.data.BackwardOrForwardItemResources
import com.mudita.compact.pagination.data.PaginationViewInfo

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun BackwardOrForwardItemCompose(
    paginationViewInfo: PaginationViewInfo,
    isBackwardIcon: Boolean,
    backwardOrForwardItemResources: BackwardOrForwardItemResources,
    backwardOrForwardItemClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {

        Box(
            modifier = Modifier
                .height(backwardOrForwardItemResources.itemContainerHeight)
                .width(backwardOrForwardItemResources.itemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(backwardOrForwardItemResources.itemHeight)
                    .width(backwardOrForwardItemResources.itemWidth)
                    .clip(
                        shape = RoundedCornerShape(backwardOrForwardItemResources.itemCornerRadius)
                    )
            ) {

                val iconPainter = if (isBackwardIcon) {
                    if (paginationViewInfo.selectedPage == 1) {
                        painterResource(id = backwardOrForwardItemResources.backwardInactiveIconResId)
                    } else {
                        painterResource(id = backwardOrForwardItemResources.backwardActiveIconResId)
                    }
                } else {
                    if (paginationViewInfo.selectedPage == paginationViewInfo.pagesSize) {
                        painterResource(id = backwardOrForwardItemResources.forwardInactiveIconResId)
                    } else {
                        painterResource(id = backwardOrForwardItemResources.forwardActiveIconResId)
                    }
                }

                Image(painter = iconPainter,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .matchParentSize()
                        .clickable(indication = if (!paginationViewInfo.animateOnPressEvent) null else LocalIndication.current,
                            interactionSource = remember {
                                MutableInteractionSource()
                            }) {
                            if (isBackwardIcon && paginationViewInfo.selectedPage != 1) {
                                backwardOrForwardItemClicked(paginationViewInfo.selectedPage - 1)
                            } else if (!isBackwardIcon && paginationViewInfo.selectedPage != paginationViewInfo.pagesSize) {
                                backwardOrForwardItemClicked(paginationViewInfo.selectedPage + 1)
                            }
                        })
            }
        }
    }

}