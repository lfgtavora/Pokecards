package com.lfgtavora.pokecards.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}