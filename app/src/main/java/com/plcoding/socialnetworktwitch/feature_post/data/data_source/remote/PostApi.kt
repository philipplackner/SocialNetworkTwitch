package com.plcoding.socialnetworktwitch.feature_post.data.data_source.remote

import com.plcoding.socialnetworktwitch.core.domain.models.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("/api/post/get")
    suspend fun getPostsForFollows(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Post>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}