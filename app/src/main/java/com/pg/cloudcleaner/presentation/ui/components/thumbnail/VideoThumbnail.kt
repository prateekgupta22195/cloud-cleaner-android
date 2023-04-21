package com.pg.cloudcleaner.presentation.ui.components.thumbnail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VideoThumbnail(
    model: Any?,
) {

    GlideImage(
        model = model.toString(),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        requestBuilderTransform = {
            it.centerCrop()
        })


//    val painter = rememberAsyncImagePainter(
//        model = model.toString(),
//        imageLoader = App.instance.imageLoader,
//        error = painterResource(id = R.drawable.ic_file)
//    )
//    val isSuccess = painter.state is AsyncImagePainter.State.Success
//    Box {
//        androidx.compose.foundation.Image(
//            painter = painter,
//            contentDescription = "video thumbnail",
//            alignment = Alignment.Center,
//            colorFilter = if (isSuccess) ColorFilter.tint(
//                color = Color.Gray, blendMode = BlendMode.Darken
//            ) else null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = if (isSuccess) ContentScale.Crop else ContentScale.None
//        )
//
//        if (isSuccess) Icon(
//            Icons.Rounded.PlayArrow,
//            contentDescription = "Play icon",
//            tint = Color.White,
//            modifier = Modifier
//                .size(48.dp)
//                .align(Alignment.Center)
//        )
//
//    }
}