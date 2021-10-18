package com.plcoding.socialnetworktwitch.feature_post.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.plcoding.socialnetworktwitch.core.domain.models.Comment
import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    val posts: Flow<PagingData<Post>>

    suspend fun createPost(description: String, imageUri: Uri): SimpleResource

    suspend fun getPostDetails(postId: String): Resource<Post>

    suspend fun getCommentsForPost(postId: String): Resource<List<Comment>>
}