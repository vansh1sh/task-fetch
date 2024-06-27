package com.vansh.fetch.util

import com.vansh.fetch.data.local.CollapsableItem
import com.vansh.fetch.data.remote.Item

object Helper {

    fun fromItemListToCollapsableListItem(itemList: List<Item>): List<CollapsableItem> {

        val filteredItemList = itemList.filter { !it.name.isNullOrBlank() }
        val listIdToItemsMap = filteredItemList.groupBy { it.listId }
        val sortedMapValues =
            listIdToItemsMap.mapValues { (_, value) -> value.sortedBy { it.name } }
        val sortedByListId = sortedMapValues.toSortedMap()

        return sortedByListId.map { (listId, items) ->
            CollapsableItem(
                listId = listId,
                itemsList = items
            )
        }
    }

}