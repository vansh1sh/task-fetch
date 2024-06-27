package com.vansh.fetch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vansh.fetch.data.local.CollapsableItem
import com.vansh.fetch.data.remote.Item
import com.vansh.fetch.repository.ItemRepository
import com.vansh.fetch.util.APIResult
import com.vansh.fetch.util.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _collapsableItems =
        MutableStateFlow<APIResult<List<CollapsableItem>>>(APIResult.Loading())

    val collapsableItems: StateFlow<APIResult<List<CollapsableItem>>> =
        _collapsableItems.asStateFlow()

    init {
        getAllItems()
    }

    private fun getAllItems() {
        viewModelScope.launch {
            itemRepository.getAllItems()
                .collect { result: APIResult<List<Item>> ->

                    when (result) {
                        is APIResult.Loading -> {
                            _collapsableItems.value = APIResult.Loading()
                        }

                        is APIResult.Success -> {
                            val items = result.data!!
                            val collapsableListItems =
                                Helper.fromItemListToCollapsableListItem(items)
                            _collapsableItems.value = APIResult.Success(collapsableListItems)
                        }

                        is APIResult.Error -> {
                            _collapsableItems.value =
                                APIResult.Error(result.message ?: "An error occurred")
                        }
                    }
                }
        }
    }

}
