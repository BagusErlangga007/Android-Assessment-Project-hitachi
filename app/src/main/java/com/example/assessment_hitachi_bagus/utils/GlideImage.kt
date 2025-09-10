package com.example.assessment_hitachi_bagus.utils

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide

@Composable
fun GlideImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        update = { imageView ->
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_delete)
                .circleCrop()
                .into(imageView)
        }
    )
}