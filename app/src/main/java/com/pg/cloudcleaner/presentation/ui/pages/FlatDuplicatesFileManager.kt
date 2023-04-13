package com.pg.cloudcleaner.presentation.ui.pages

import android.os.Environment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pg.cloudcleaner.data.model.LocalFile
import com.pg.cloudcleaner.presentation.ui.components.BackNavigationIcon
import com.pg.cloudcleaner.presentation.ui.components.SelectableFileItem
import com.pg.cloudcleaner.presentation.vm.FlatDuplicatesFileManagerViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun FlatFileManager() {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Duplicate Files") },
            navigationIcon = { BackNavigationIcon() },
        )
    }, bottomBar = {
        DeleteButton()
    }) { padding ->
        Column(modifier = Modifier.padding(padding)) { FileListView() }
    }
    Timber.d(Environment.getExternalStorageDirectory().absolutePath)
}


@Composable
fun DeleteButton(vm: FlatDuplicatesFileManagerViewModel = viewModel()) {
    val selectedFileIds = remember { vm.selectedFileIds }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue, contentColor = Color.White
            ), onClick = {
                vm.deleteFiles(selectedFileIds)
            }, enabled = selectedFileIds.isNotEmpty(), modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = if (selectedFileIds.isEmpty()) "Delete" else "Delete ${selectedFileIds.size} Files")
        }
    }

}

@Composable
fun FileListView(vm: FlatDuplicatesFileManagerViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val list = vm.readFiles().collectAsState(initial = emptyMap())
    LaunchedEffect(key1 = Unit, block = {
        scope.launch(Dispatchers.IO + CoroutineExceptionHandler { a, b ->
            Timber.d("ABc hello")
            vm.readFiles()
        }) {
            Timber.d("hole" + Thread.currentThread().name)
        }
    })

    val lazyState = rememberLazyListState()

    LazyColumn(state = lazyState) {
        items(list.value.keys.size) {
            val key = list.value.keys.toList()[it]
            key(key) {
                HorizontalDuplicateFiles(list.value[key]!!)
            }
        }

    }
}


@Composable
fun HorizontalDuplicateFiles(
    data: List<LocalFile>, vm: FlatDuplicatesFileManagerViewModel = viewModel()
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("${data.size} Duplicates")
        Box(modifier = Modifier.height(4.dp))
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(data.size) {
                val selectedFileIds = remember {
                    vm.selectedFileIds
                }
                SelectableFileItem(data[it],
                    isSelected = selectedFileIds.contains(data[it].id),
                    onCheckedChangeListener = { checked ->
                        if (checked) vm.selectedFileIds.add(data[it].id)
                        else vm.selectedFileIds.remove(data[it].id)
                    })
            }
        }
    }
}



