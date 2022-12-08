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
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewBackwardOrForwardItemResources
import com.sargis.khlopuzyan.pagination_view.data.PaginationViewInfo

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun BackwardOrForwardItemCompose(
    paginationViewInfo: PaginationViewInfo,
    isBackwardIcon: Boolean,
    paginationViewBackwardOrForwardItemResources: PaginationViewBackwardOrForwardItemResources,
    backwardOrForwardItemClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {

        Box(
            modifier = Modifier
                .height(paginationViewBackwardOrForwardItemResources.backwardOrForwardItemContainerHeight)
                .width(paginationViewBackwardOrForwardItemResources.backwardOrForwardItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(paginationViewBackwardOrForwardItemResources.backwardOrForwardItemHeight)
                    .width(paginationViewBackwardOrForwardItemResources.backwardOrForwardItemWidth)
                    .clip(
                        shape = RoundedCornerShape(paginationViewBackwardOrForwardItemResources.backwardOrForwardItemCornerRadius)
                    )
            ) {

                val iconPainter = if (isBackwardIcon) {
                    if (paginationViewInfo.selectedPage == 1) {
                        painterResource(id =paginationViewBackwardOrForwardItemResources.backwardInactiveIconResId)
                    } else {
                        painterResource(id = paginationViewBackwardOrForwardItemResources.backwardActiveIconResId)
                    }
                } else {
                    if (paginationViewInfo.selectedPage == paginationViewInfo.pagesSize) {
                        painterResource(id = paginationViewBackwardOrForwardItemResources.forwardInactiveIconResId)
                    } else {
                        painterResource(id = paginationViewBackwardOrForwardItemResources.forwardActiveIconResId)
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