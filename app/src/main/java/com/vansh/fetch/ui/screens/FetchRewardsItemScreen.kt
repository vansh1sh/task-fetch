package com.vansh.fetch.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vansh.fetch.ui.components.FetchRewardsItemList
import com.vansh.fetch.util.APIResult
import com.vansh.fetch.viewmodel.ItemsViewModel

@Composable
fun FetchRewardsItemScreen(
    itemsViewModel: ItemsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val listItems by itemsViewModel.collapsableItems.collectAsState()

    val (isLoading, collapsableItemList) = when (listItems) {
        is APIResult.Loading -> true to emptyList()
        is APIResult.Success -> false to listItems.data.orEmpty()
        is APIResult.Error -> {
            Toast.makeText(context, listItems.message.orEmpty(), Toast.LENGTH_LONG).show()
            false to emptyList()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(64.dp),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else if (collapsableItemList.isNotEmpty()) {
                FetchRewardsItemList(collapsableItems = collapsableItemList)
            }
        }
    }
}
