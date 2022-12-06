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
import androidx.compose.ui.unit.Dp
import com.sargis.khlopuzyan.pagination_view.R

/**
 * Created by Sargis Khlopuzyan on 12/5/2022.
 */
@Composable
fun BackwardOrForwardItemCompose(
    selectedPosition: Int,
    itemsSize: Int,
    isBackwardIcon: Boolean,
    animateOnPressEvent: Boolean,

    backwardOrForwardItemContainerWidth: Dp,
    backwardOrForwardItemContainerHeight: Dp,
    backwardOrForwardItemWidth: Dp,
    backwardOrForwardItemHeight: Dp,
    spaceBetweenBackwardOrForwardItemAndPaginationItem: Dp,
    backwardOrForwardItemCornerRadius: Dp,

    backwardOrForwardItemClicked: (page: Int) -> Unit
) {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {

        if (!isBackwardIcon) {
            Spacer(
                modifier = Modifier.width(
                    spaceBetweenBackwardOrForwardItemAndPaginationItem
                )
            )
        }

        Box(
            modifier = Modifier
                .height(backwardOrForwardItemContainerHeight)
                .width(backwardOrForwardItemContainerWidth),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(backwardOrForwardItemHeight)
                    .width(backwardOrForwardItemWidth)
                    .clip(
                        shape = RoundedCornerShape(backwardOrForwardItemCornerRadius)
                    )
            ) {

                val iconPainter = if (isBackwardIcon) {
                    if (selectedPosition == 1) {
                        painterResource(id = R.drawable.ic_arrow_left_inactive)
                    } else {
                        painterResource(id = R.drawable.ic_arrow_left_active)
                    }
                } else {
                    if (selectedPosition == itemsSize) {
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
                            indication = if (!animateOnPressEvent) null else LocalIndication.current, interactionSource = remember { MutableInteractionSource() }
                        ) {
                            if (isBackwardIcon && selectedPosition != 1) {
                                backwardOrForwardItemClicked(selectedPosition - 1)
                            } else if (!isBackwardIcon && selectedPosition != itemsSize) {
                                backwardOrForwardItemClicked(selectedPosition + 1)
                            }
                        }
                )
            }
        }

        if (isBackwardIcon) {
            Spacer(
                modifier = Modifier.width(
                    spaceBetweenBackwardOrForwardItemAndPaginationItem
                )
            )
        }

    }

}