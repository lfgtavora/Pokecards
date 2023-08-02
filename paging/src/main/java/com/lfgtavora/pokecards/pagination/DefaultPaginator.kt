package com.lfgtavora.pokecards.pagination

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<Item>,
    private inline val getNextKey: suspend (Item) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (result: Item, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {

            if (isMakingRequest) {
                return
            }
            isMakingRequest = true
            onLoadUpdated(true)
            val response = onRequest(currentKey)
            isMakingRequest = false
            val result = response.getOrElse {
                onError(it)
                onLoadUpdated(false)
                return
            }


            onSuccess(result, currentKey)
            onLoadUpdated(false)
            currentKey = getNextKey(result)
    }

    override fun reset() {
        currentKey = initialKey
    }
}