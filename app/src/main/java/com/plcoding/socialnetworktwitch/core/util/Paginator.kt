package com.plcoding.socialnetworktwitch.core.util

interface Paginator<T> {

    suspend fun loadNextItems()
}