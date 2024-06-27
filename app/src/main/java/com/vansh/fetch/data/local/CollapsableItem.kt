package com.vansh.fetch.data.local

import com.vansh.fetch.data.remote.Item

data class CollapsableItem(
    val listId: Int,
    val itemsList: List<Item>
)
