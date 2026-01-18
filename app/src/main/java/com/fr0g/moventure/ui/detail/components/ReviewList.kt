package com.fr0g.moventure.ui.detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fr0g.moventure.detail.domain.models.Review

@Composable
fun ReviewList(reviews: List<Review>) {
    var visibleCount by remember { mutableIntStateOf(2) }

    val displayReviews = reviews.take(visibleCount)

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .animateContentSize()
    ) {
        displayReviews.forEach { review ->
            ReviewItem(review = review)
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (reviews.size > 2) {
            TextButton(
                onClick = {
                    if (visibleCount < reviews.size) {
                        visibleCount += 3
                    } else {
                        visibleCount = 2
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (visibleCount < reviews.size)
                        "Load More Reviews (+3)"
                    else "Show Less"
                )
            }
        }
    }
}