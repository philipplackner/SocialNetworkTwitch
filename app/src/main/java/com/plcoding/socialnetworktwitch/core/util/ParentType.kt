package com.plcoding.data.util

sealed class ParentType(val type: Int) {
    object Post : ParentType(0)
    object Comment : ParentType(1)
    object None : ParentType(2)

    companion object {
        fun fromType(type: Int): ParentType {
            return when(type) {
                0 -> Post
                1 -> Comment
                else -> None
            }
        }
    }
}
