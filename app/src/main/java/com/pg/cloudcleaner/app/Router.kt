package com.pg.cloudcleaner.app

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pg.cloudcleaner.app.Routes.Companion.DOCUMENT_VIEWER
import com.pg.cloudcleaner.app.Routes.Companion.FILE_DETAIL_VIEWER
import com.pg.cloudcleaner.app.Routes.Companion.FLAT_DUPLICATES_FILE_MANAGER
import com.pg.cloudcleaner.app.Routes.Companion.FLAT_IMAGES_FILE_MANAGER
import com.pg.cloudcleaner.app.Routes.Companion.FLAT_VIDEOS_FILE_MANAGER
import com.pg.cloudcleaner.app.Routes.Companion.HOME
import com.pg.cloudcleaner.app.Routes.Companion.PDF_VIEWER
import com.pg.cloudcleaner.presentation.ui.components.DocumentViewer
import com.pg.cloudcleaner.presentation.ui.components.PdfView
import com.pg.cloudcleaner.presentation.ui.pages.*


val router: NavGraphBuilder.() -> Unit = {
    composable(FLAT_DUPLICATES_FILE_MANAGER) {
        FlatFileManager()
    }
    composable(FLAT_VIDEOS_FILE_MANAGER) {
        FlatVideosFileManager()
    }
    composable(FLAT_IMAGES_FILE_MANAGER) {
        FlatImagesFileManager()
    }
    composable(HOME) {
        DataCategories()
    }
    composable("$DOCUMENT_VIEWER?url={url}",
        arguments = listOf(navArgument("url") { type = NavType.StringType })
    ) { backStackEntry ->
        val url = backStackEntry.arguments?.getString("url")!!
        val resolvedUrl = Uri.decode(url)
        DocumentViewer(resolvedUrl)
    }
    composable(
        "$FILE_DETAIL_VIEWER?url={url}", arguments = listOf(
            navArgument("url") { type = NavType.StringType },
        )
    ) { backStackEntry ->
        val url = backStackEntry.arguments?.getString("url")!!
        val resolvedUrl = Uri.decode(url)
        FileDetailViewer(resolvedUrl)
    }
    composable("$PDF_VIEWER?url={url}",
        arguments = listOf(navArgument("url") { type = NavType.StringType })
    ) { backStackEntry ->
        val url = backStackEntry.arguments?.getString("url")!!
        val resolvedUrl = Uri.decode(url)
        PdfView(resolvedUrl)
    }
}


interface Routes {
    companion object {
        const val FLAT_DUPLICATES_FILE_MANAGER = "/flat-duplicates-file-manager"
        const val FLAT_IMAGES_FILE_MANAGER = "/flat-images-file-manager"
        const val FLAT_VIDEOS_FILE_MANAGER = "/flat-videos-file-manager"
        const val HOME = "/home"
        const val DOCUMENT_VIEWER = "/document-viewer"
        const val FILE_DETAIL_VIEWER = "/file-detail-viewer"
        const val PDF_VIEWER = "/pdf-viewer"
    }
}
