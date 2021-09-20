package com.plcoding.socialnetworktwitch.feature_post.domain.repository

import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.core.util.Resource

interface PostRepository {

    suspend fun getPostsForFollows(
        page: Int = 0,
        pageSize: Int = Constants.PAGE_SIZE_POSTS
    ): Resource<List<Post>>
}